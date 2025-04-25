package com.keepgoing.keepserver.global.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ItemUsageFieldValidator.class)
public @interface ValidateItem {
    String message() default "사용 필드 유효성 검증 실패";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
