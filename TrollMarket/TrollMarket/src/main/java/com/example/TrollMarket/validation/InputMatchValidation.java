package com.example.TrollMarket.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class InputMatchValidation implements ConstraintValidator<InputMatch,Object> {
    private String first;
    private String second;

    @Override
    public void initialize(InputMatch constraintAnnotation){
        this.first = constraintAnnotation.first();
        this.second = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext){

        try{
            var firstValue = new BeanWrapperImpl(object).getPropertyValue(first);
            var secondValue = new BeanWrapperImpl(object).getPropertyValue(second);

            return (firstValue == null && secondValue == null)|| (firstValue != null && firstValue.equals(secondValue));
        }catch (Exception exception){
            return false;
        }
    }
}
