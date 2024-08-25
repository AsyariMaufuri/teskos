package com.example.TrollMarket.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InputIntegerValidation implements ConstraintValidator<InputInteger,Integer> {
    @Override
    public void initialize(InputInteger constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer quantity, ConstraintValidatorContext constraintValidatorContext) {
//        return !service.checkExistingUsername(username);
        return false;
    }
}
