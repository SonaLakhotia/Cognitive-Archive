package com.softwaretesting.testing.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = {CustomerValidator.class})
@ExtendWith(SpringExtension.class)
class CustomerValidatorTest {

    private CustomerValidator customerValidator;

    // Comment for test!
    @BeforeEach
    void setup() {
        customerValidator = new CustomerValidator();
    }

    @Test
    void validate404TestThrowsException() {
        Optional<String> object = Optional.empty();
        String label = "User-Name";
        String value = "Peter";
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                customerValidator.validate404(object, label, value)
        );
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("java.util.Optional with User-Name'Peter' does not exist.", exception.getReason());
    }

    /**
     * Method under test: {@link CustomerValidator#validate404(Optional, String, String)}
     */
    @Test
    void testValidate404() {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by validate404(Optional, String, String)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        customerValidator.validate404(Optional.of("Value"), "Label", "42");
    }

    /**
     * Method under test: {@link CustomerValidator#validate404(Optional, String, String)}
     */
    @Test
    void testValidate4042() {
        assertThrows(ResponseStatusException.class, () -> customerValidator.validate404(Optional.empty(), "Label", "42"));
    }

    @Test
    void validate404NoException() {
        Optional<String> object = Optional.of("value");
        String label = "User-Name";
        String value = "Peter";
        assertDoesNotThrow(() ->
                customerValidator.validate404(object, label, value)
        );
    }
}