package com.yberdaliyev.common.validators;

import com.yberdaliyev.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EmailExistsValidator implements ConstraintValidator<EmailExists, String> {
    private LoginRepository repository;

    @Autowired
    public void setRepository(LoginRepository repository) {this.repository = repository;}

    @Override
    public void initialize(EmailExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ( value.isEmpty() ) return true;
        return !repository.existsByEmail(value);
    }
}
