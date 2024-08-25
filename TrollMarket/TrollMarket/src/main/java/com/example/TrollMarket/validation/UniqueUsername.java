package com.example.TrollMarket.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = UniqueUsernameValidation.class)
public @interface UniqueUsername {
    public String message();
    public Class<?>[] groups() default {};
    public Class<? extends Payload> [] payload() default {};
}
