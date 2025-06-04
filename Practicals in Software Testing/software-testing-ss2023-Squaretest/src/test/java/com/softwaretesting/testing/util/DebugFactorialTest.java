package com.softwaretesting.testing.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DebugFactorialTest {

    @Test
    public void testFactorial() {
        // Test case for n = 0
        assertEquals(1, DebugFactorial.factorial(0));

        // Test case for n = 1
        assertEquals(1, DebugFactorial.factorial(1));

        // Test case for n > 1
        assertEquals(120, DebugFactorial.factorial(5));
    }
}
