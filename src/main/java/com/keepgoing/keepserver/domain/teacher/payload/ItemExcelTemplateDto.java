package com.keepgoing.keepserver.domain.teacher.payload;

public record ItemExcelTemplateDto(
        String serialNumber,
        String acquisitionDate,
        int price,
        String item,
        String details,
        String status,
        String rentedBy,
        String place,
        String returnDate,
        String rentalDate,
        String usageDate
){
    public ItemExcelTemplateDto() {
        this("45211503-2119175",
             "YYYY.M.D",
             2221805,
             "노트북컴퓨터",
             "LG GRAM15",
             "AVAILABLE/ UNAVAILABLE / IN_USE",
             "상태가 IN_USE 일 경우만 입력해주세요.",
             "개인휴대 or 기기 보관 위치",
             "상태가 IN_USE 일 경우만 입력해주세요.",
             "상태가 IN_USE 일 경우만 입력해주세요.",
             "상태가 IN_USE 일 경우만 입력해주세요. (함수 사용 가능 =I2-J2)");
    }
}
