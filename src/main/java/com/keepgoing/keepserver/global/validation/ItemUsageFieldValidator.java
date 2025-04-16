package com.keepgoing.keepserver.global.validation;

import com.keepgoing.keepserver.domain.teacher.payload.ItemExcelDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ItemUsageFieldValidator implements ConstraintValidator<ValidateItem, ItemExcelDto> {

    @Override
    public boolean isValid(ItemExcelDto dto, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        return switch (dto.status()) {
            case IN_USE -> validateRequiredWhenInUse(dto, context);
            case AVAILABLE, UNAVAILABLE -> validateEmptyWhenNotInUse(dto, context);
        };
    }

    private boolean validateRequiredWhenInUse(ItemExcelDto dto, ConstraintValidatorContext context) {
        boolean valid = dto.rentalDate() != null &&
                dto.returnDate() != null &&
                !isBlank(dto.rentedBy()) &&
                dto.usageDate() != null;

        if (valid)
            return true;

        if (dto.rentalDate() == null) {
            context.buildConstraintViolationWithTemplate("대여일은 필수입니다 (상태가 IN_USE일 때)")
                   .addPropertyNode("rentalDate").addConstraintViolation();
        }
        if (dto.returnDate() == null) {
            context.buildConstraintViolationWithTemplate("반납일은 필수입니다 (상태가 IN_USE일 때)")
                   .addPropertyNode("returnDate").addConstraintViolation();
        }
        if (isBlank(dto.rentedBy())) {
            context.buildConstraintViolationWithTemplate("대여자 입력은 필수입니다 (상태가 IN_USE일 때)")
                   .addPropertyNode("rentedBy").addConstraintViolation();
        }
        if (dto.usageDate() == null) {
            context.buildConstraintViolationWithTemplate("사용일수는 필수입니다 (상태가 IN_USE일 때)")
                   .addPropertyNode("usageDate").addConstraintViolation();
        }
        return false;
    }

    private boolean validateEmptyWhenNotInUse(ItemExcelDto dto, ConstraintValidatorContext context) {
        boolean valid = dto.rentalDate() == null &&
                dto.returnDate() == null &&
                isBlank(dto.rentedBy()) &&
                dto.usageDate() == null;

        if (valid) return true;

        if (dto.rentalDate() != null) {
            context.buildConstraintViolationWithTemplate("대여일은 상태가 IN_USE가 아닐 경우 입력하지 마세요")
                   .addPropertyNode("rentalDate").addConstraintViolation();
        }
        if (dto.returnDate() != null) {
            context.buildConstraintViolationWithTemplate("반납일은 상태가 IN_USE가 아닐 경우 입력하지 마세요")
                   .addPropertyNode("returnDate").addConstraintViolation();
        }
        if (!isBlank(dto.rentedBy())) {
            context.buildConstraintViolationWithTemplate("대여자는 상태가 IN_USE가 아닐 경우 입력하지 마세요")
                   .addPropertyNode("rentedBy").addConstraintViolation();
        }
        if (dto.usageDate() != null) {
            context.buildConstraintViolationWithTemplate("사용일수는 상태가 IN_USE가 아닐 경우 입력하지 마세요")
                   .addPropertyNode("usageDate").addConstraintViolation();

        }
        return false;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
