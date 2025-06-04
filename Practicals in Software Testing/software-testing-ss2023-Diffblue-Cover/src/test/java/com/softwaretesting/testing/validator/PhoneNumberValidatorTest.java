package com.softwaretesting.testing.validator;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {PhoneNumberValidator.class})
@ExtendWith(SpringExtension.class)
class PhoneNumberValidatorTest {

    @Autowired
    private PhoneNumberValidator phoneNumberValidator;

    private PhoneNumberValidator pValidator = new PhoneNumberValidator();

    @Test
    void validateTrue() {
        boolean validated = pValidator.validate("+4912347788669");
        assertTrue(validated);
        Pattern pattern = Pattern.compile("^\\+49\\d{11}$");
        Matcher matcher = pattern.matcher("+4912345678999");
        assertTrue(matcher.matches());
    }

    /**
     * Method under test: {@link PhoneNumberValidator#validate(String)}
     */
    @Test
    void testValidate() {
        assertFalse(phoneNumberValidator.validate("42"));
        assertTrue(phoneNumberValidator.validate("+4999999999999"));
    }

    @Test
    void validateFalse() {
        boolean validated = pValidator.validate("+98123");
        assertFalse(validated);
        Pattern pattern = Pattern.compile("^\\+49\\d{11}$");
        Matcher matcher = pattern.matcher("+49123");
        assertFalse(matcher.matches());
    }

    @Test
    void validateWrongLength() {
        boolean validated = pValidator.validate("+49123234567234567456");
        assertFalse(validated);
        Pattern pattern = Pattern.compile("^\\+49\\d{11}$");
        Matcher matcher = pattern.matcher("+49123234567234567456");
        assertFalse(matcher.matches());
    }

    @Test
    void validateWrongStr() {
        boolean validated = pValidator.validate("+4912323abc@");
        assertFalse(validated);
        Pattern pattern = Pattern.compile("^\\+49\\d{11}$");
        Matcher matcher = pattern.matcher("+4912323abc@");
        assertFalse(matcher.matches());
    }
}