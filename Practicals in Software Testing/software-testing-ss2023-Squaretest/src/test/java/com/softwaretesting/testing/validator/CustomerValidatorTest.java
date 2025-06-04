package com.softwaretesting.testing.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CustomerValidatorTest {

    private CustomerValidator customerValidator;

    // Comment for test!
    @BeforeEach
    void setup(){
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