package com.softwaretesting.testing.util;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MiscTest {
    @Test
    void sumTest() {
        int result = Misc.sum(2, 2);
        assertEquals(4, result);
    }

    @Test
    void sumInvalid() {
        int result = Misc.sum(2, 2);
        assertNotEquals(8, result);
    }

    @Test
    void sumDouble() {
        int result = Misc.sum(2, 2);
        assertNotEquals(8.0, result);
    }

    @Test
    void sumZero() {
        int result = Misc.sum(2, 2);
        assertNotEquals(0, result);
    }

    /**
     * Method under test: {@link Misc#sum(int, int)}
     */
    @Test
    void testSum() {
        assertEquals(2, Misc.sum(1, 1));
        assertEquals(4, Misc.sum(3, 1));
        assertEquals(1, Misc.sum(0, 1));
        assertEquals(0, Misc.sum(-1, 1));
    }

    @Test
    void divide() {
        assertEquals(1.0, Misc.divide(10, 10));
        assertNotEquals(7.0, Misc.divide(10, 10));
        assertNotEquals(0, Misc.divide(10, 10));
        assertThrows(RuntimeException.class, () -> Misc.divide(11, 0));
    }

    /**
     * Method under test: {@link Misc#divide(int, int)}
     */
    @Test
    void testDivide() {
        assertEquals(1.0d, Misc.divide(1, 1));
        assertThrows(RuntimeException.class, () -> Misc.divide(1, 0));
        assertEquals(3.0d, Misc.divide(3, 1));
        assertEquals(-1.0d, Misc.divide(-1, 1));
    }


    @Test
    void isColorSupported() {
        assertTrue(Misc.isColorSupported(Misc.Color.BLUE));
        assertTrue(Misc.isColorSupported(Misc.Color.RED));
        assertTrue(Misc.isColorSupported(Misc.Color.YELLOW));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> Misc.isColorSupported(null));
        assertEquals("color cannot be null", exception.getMessage());
        assertFalse(Misc.isColorSupported(Misc.Color.GREEN));
    }

    /**
     * Method under test: {@link Misc#isColorSupported(Misc.Color)}
     */
    @Test
    void testIsColorSupported() {
        assertTrue(Misc.isColorSupported(Misc.Color.RED));
        assertTrue(Misc.isColorSupported(Misc.Color.YELLOW));
        assertTrue(Misc.isColorSupported(Misc.Color.BLUE));
        assertFalse(Misc.isColorSupported(Misc.Color.GREEN));
    }

    /**
     * Method under test: {@link Misc#isColorSupported(Misc.Color)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testIsColorSupported2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: color cannot be null
        //       at org.springframework.util.Assert.isTrue(Assert.java:118)
        //       at com.softwaretesting.testing.util.Misc.isColorSupported(Misc.java:27)
        //   In order to prevent isColorSupported(Color)
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   isColorSupported(Color).
        //   See https://diff.blue/R013 to resolve this issue.

        Misc.isColorSupported(null);
    }

    @Test
    void calculateFactorial() {
        assertEquals(3628800L, Misc.calculateFactorial(10));
    }

    @Test
    void calculateFactorialInValid() {
        assertNotEquals(362, Misc.calculateFactorial(6));
    }

    /**
     * Method under test: {@link Misc#calculateFactorial(int)}
     */
    @Test
    void testCalculateFactorial() {
        assertEquals(3628800L, Misc.calculateFactorial(10));
    }

    @Test
    void isPrime() {
        assertFalse(Misc.isPrime(15, 2));
        assertTrue(Misc.isPrime(13, 2));
        assertFalse(Misc.isPrime(0, 2));
        assertFalse(Misc.isPrime(1, 2));
        assertTrue(Misc.isPrime(2, 1));
        assertFalse(Misc.isPrime(1, 1));

    }

    /**
     * Method under test: {@link Misc#isPrime(int, int)}
     */
    @Test
    void testIsPrime() {
        assertFalse(Misc.isPrime(1, 1));
        assertTrue(Misc.isPrime(2, 1));
    }

    @Test
    void isEven() {
        assertTrue(Misc.isEven(6));
        assertFalse(Misc.isEven(3));
    }

    /**
     * Method under test: {@link Misc#isEven(int)}
     */
    @Test
    void testIsEven() {
        assertFalse(Misc.isEven(1));
        assertTrue(Misc.isEven(2));
    }
}
