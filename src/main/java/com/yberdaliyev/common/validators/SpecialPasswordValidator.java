package com.yberdaliyev.common.validators;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;


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
