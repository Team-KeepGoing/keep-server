package com.keepgoing.keepserver.domain.teacher.payload;

import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
import com.keepgoing.keepserver.global.validation.ValidateItem;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@ValidateItem
public record ItemExcelDto(
        @NotBlank(message = "시리얼 번호는 필수 항목입니다")
        String serialNumber,

        @PastOrPresent(message = "취득 날짜는 현재 또는 과거 날짜여야 합니다")
        LocalDate acquisitionDate,

        @Min(value = 0, message = "가격은 0 이상이어야 합니다")
        Long price,

        @NotBlank(message = "품목명은 필수 항목입니다")
        String item,

        @NotBlank(message = "세부 제품명은 필수 항목입니다")
        String details,

        @NotNull(message = "상태는 필수 항목입니다")
        ItemStatus status,

        String rentedBy,

        @NotNull(message = "기기 위치는 필수 항목입니다")
        String place,

        LocalDate returnDate,
        LocalDate rentalDate,
        Long usageDate
) {}
