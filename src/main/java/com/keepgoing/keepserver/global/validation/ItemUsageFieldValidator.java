package com.keepgoing.keepserver.global.validation;

import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
import com.keepgoing.keepserver.domain.teacher.payload.ItemExcelDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ItemUsageFieldValidator implements ConstraintValidator<ValidateItem, ItemExcelDto> {

    @Override
    public boolean isValid(ItemExcelDto dto, ConstraintValidatorContext context) {
        if (dto.status() == ItemStatus.IN_USE) {
            boolean valid = dto.rentalDate() != null
                    && dto.returnDate() != null
                    && dto.rentedBy() != null && !dto.rentedBy().isBlank()
                    && dto.usageDate() != null;

            if (!valid) {
                context.disableDefaultConstraintViolation();
                if (dto.rentalDate() == null) {
                    context.buildConstraintViolationWithTemplate("대여일은 필수입니다 (상태가 IN_USE일 때)")
                           .addPropertyNode("rentalDate").addConstraintViolation();
                }
                if (dto.returnDate() == null) {
                    context.buildConstraintViolationWithTemplate("반납일은 필수입니다 (상태가 IN_USE일 때)")
                           .addPropertyNode("returnDate").addConstraintViolation();
                }
                if (dto.rentedBy() == null) {
                    context.buildConstraintViolationWithTemplate("대여자 입력은 필수입니다 (상태가 IN_USE일 때)")
                           .addPropertyNode("rentedBy").addConstraintViolation();
                }
                if (dto.usageDate() == null) {
                    context.buildConstraintViolationWithTemplate("사용일수는 필수입니다 (상태가 IN_USE일 때)")
                           .addPropertyNode("usageDate").addConstraintViolation();
                }
            }

            return valid;
        }

        return true;
    }
}
