package dev.stelmach.tweeditapi.validation;

import dev.stelmach.tweeditapi.helper.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String> {

    @Autowired
    private DataHelper dataHelper;

    @Override
    public void initialize(UniqueUser constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return dataHelper.isUserUnique(username);
    }

}
