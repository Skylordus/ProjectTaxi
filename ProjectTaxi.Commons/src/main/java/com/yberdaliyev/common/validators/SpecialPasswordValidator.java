package com.yberdaliyev.common.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class SpecialPasswordValidator implements ConstraintValidator<SpecialPasswordConstraint, String> {


    @Override
    public void initialize(SpecialPasswordConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value==null) return false;
        if (value.equals("hiber")) {return true;}
        else {
            return false;
        }

    }
}
