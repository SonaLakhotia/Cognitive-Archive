package com.softwaretesting.testing.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiscTest {
    @Test
    void sumTest() {
        int result = Misc.sum(2,2);
        assertEquals(4, result);
    }

    @Test
    void sumInvalid() {
        int result = Misc.sum(2,2);
        assertNotEquals(8, result);
    }

    @Test
    void sumDouble() {
        int result = Misc.sum(2,2);
        assertNotEquals(8.0, result);
    }

    @Test
    void sumZero() {
        int result = Misc.sum(2,2);
        assertNotEquals(0, result);
    }

    @Test
    void divide() {
        assertEquals(1.0, Misc.divide(10,10));
        assertNotEquals(7.0, Misc.divide(10,10));
        assertNotEquals(0, Misc.divide(10,10));
        assertThrows(RuntimeException.class, ()-> Misc.divide(11,0));
    }


    @Test
    void isColorSupported() {
        assertTrue(Misc.isColorSupported(Misc.Color.BLUE));
        assertTrue(Misc.isColorSupported(Misc.Color.RED));
        assertTrue(Misc.isColorSupported(Misc.Color.YELLOW));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> Misc.isColorSupported(null));
        assertEquals("color cannot be null", exception.getMessage());
        assertFalse(Misc.isColorSupported(Misc.Color.GREEN));
    }

    @Test
    void calculateFactorial() {
        assertEquals(3628800L, Misc.calculateFactorial(10));
    }

    @Test
    void calculateFactorialInValid() {
        assertNotEquals(362, Misc.calculateFactorial(6));
    }

    @Test
    void isPrime() {
        assertFalse(Misc.isPrime(15,2));
        assertTrue(Misc.isPrime(13,2));
        assertFalse(Misc.isPrime(0,2));
        assertFalse(Misc.isPrime(1, 2));
        assertTrue(Misc.isPrime(2,1));
        assertFalse(Misc.isPrime(1,1));

    }

    @Test
    void isEven() {
        assertTrue(Misc.isEven(6));
        assertFalse(Misc.isEven(3));
    }
}
