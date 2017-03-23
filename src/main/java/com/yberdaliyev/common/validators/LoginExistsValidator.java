package com.yberdaliyev.common.validators;

import com.yberdaliyev.models.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class LoginExistsValidator implements ConstraintValidator<LoginExists, String> {
private LoginRepository repository;

    @Autowired
    public void setRepository(LoginRepository repository) {this.repository = repository;}

    @Override
    public void initialize(LoginExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ( value.isEmpty() ) return true;
        return !repository.existsByLogin(value);
    }
}
