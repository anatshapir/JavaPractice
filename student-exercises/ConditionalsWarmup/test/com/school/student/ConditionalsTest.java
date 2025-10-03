package com.school.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConditionalsTest {

    private Conditionals conditionals;

    @BeforeEach
    void setUp() {
        conditionals = new Conditionals();
    }

    @Test
    @DisplayName("describe() differentiates positive, negative, and zero")
    void describeNumbers() {
        assertEquals("positive", conditionals.describe(5));
        assertEquals("negative", conditionals.describe(-3));
        assertEquals("zero", conditionals.describe(0));
    }

    @Test
    @DisplayName("max() returns the larger integer")
    void maxNumbers() {
        assertEquals(10, conditionals.max(10, 3));
        assertEquals(7, conditionals.max(4, 7));
        assertEquals(-2, conditionals.max(-2, -2));
    }

    @Test
    @DisplayName("withinTen() checks the inclusive range")
    void withinTen() {
        assertTrue(conditionals.withinTen(10));
        assertTrue(conditionals.withinTen(-10));
        assertFalse(conditionals.withinTen(11));
        assertFalse(conditionals.withinTen(-11));
    }
}
