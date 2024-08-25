package com.example.TrollMarket.validation;

import com.example.TrollMarket.service.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidation implements ConstraintValidator<UniqueUsername,String> {
    private final AccountService service;

    public UniqueUsernameValidation(AccountService service) {
        this.service = service;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !service.checkExistingUsername(username);
    }
}
