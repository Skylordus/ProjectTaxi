package com.yberdaliyev.common.validators;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;


public class SpecialPasswordValidator implements ConstraintValidator<SpecialPasswordConstraint, Object> {


    @Override
    public void initialize(SpecialPasswordConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}
