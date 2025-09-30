package com.school.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArrayStatsTest {

    private final ArrayStats stats = new ArrayStats();

    @Test
    @DisplayName("sum() totals positive numbers")
    void sumPositiveNumbers() {
        assertEquals(21, stats.sum(new int[] { 1, 2, 3, 4, 5, 6 }));
    }

    @Test
    @DisplayName("average() works with mixed numbers")
    void averageMixedNumbers() {
        assertEquals(2.5, stats.average(new int[] { 1, 2, 3, 4 }), 0.0001);
    }

    @Test
    @DisplayName("max() returns the largest value")
    void maxPositiveNumbers() {
        assertEquals(9, stats.max(new int[] { 1, 9, 3, 5 }));
    }
}
