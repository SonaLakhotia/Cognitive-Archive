package com.softwaretesting.testing.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DebugFactorialTest {

    @Test
    public void testFactorial() {
        // Test case for n = 0
        assertEquals(1, DebugFactorial.factorial(0));

        // Test case for n = 1
        assertEquals(1, DebugFactorial.factorial(1));

        // Test case for n > 1
        assertEquals(120, DebugFactorial.factorial(5));
        org.junit.jupiter.api.Assertions.assertEquals(1, DebugFactorial.factorial(1));
        org.junit.jupiter.api.Assertions.assertEquals(2, DebugFactorial.factorial(2));
    }
}
