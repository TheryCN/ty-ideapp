package com.github.therycn.tyideapp;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class UserRegistrationTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validate_AllOK_NoConstraintViolation() {
        // Given
        UserRegistration userRegistration = new UserRegistration("Thery", "thery@github.com", "ChangeIt#01");

        // When
        Set<ConstraintViolation<UserRegistration>> violations = validator.validate(userRegistration);

        // Then
        Assertions.assertThat(violations).isEmpty();
    }

    @Test
    public void validate_PasswordHasNoDigits_PasswordDigitsConstraintViolation() {
        // Given
        UserRegistration userRegistration = new UserRegistration("Thery", "thery@github.com", "ChangeIt");

        // When
        Set<ConstraintViolation<UserRegistration>> contstraintViolations = validator.validate(userRegistration);

        // Then
        List<String> violations = contstraintViolations.stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        Assertions.assertThat(violations).containsExactly("user.validation.password.numeric");
    }

    @Test
    public void validate_PasswordHasNoMinimumLength_PasswordLengthConstraintViolation() {
        // Given
        UserRegistration userRegistration = new UserRegistration("Thery", "thery@github.com", "abc01");

        // When
        Set<ConstraintViolation<UserRegistration>> contstraintViolations = validator.validate(userRegistration);

        // Then
        List<String> violations = contstraintViolations.stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        Assertions.assertThat(violations).containsExactly("user.validation.password.length");
    }
}
