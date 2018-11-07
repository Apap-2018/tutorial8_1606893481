package com.apap.tutorial6.security;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Created by esak on 11/7/2018.
 * source : https://www.baeldung.com/registration-password-strength-and-rules
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new DigitCharacterRule(1),
                new NumericalSequenceRule(7,false),
                new AlphabeticalSequenceRule(7,false)));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(String.join(", ",validator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }
}
