package com.school.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArrayStatsEdgeCasesTest {

    private final ArrayStats stats = new ArrayStats();

    @Test
    @DisplayName("sum() handles empty arrays")
    void sumEmptyArray() {
        assertEquals(0, stats.sum(new int[0]));
    }

    @Test
    @DisplayName("average() handles negative values")
    void averageNegativeNumbers() {
        assertEquals(-2.5, stats.average(new int[] { -1, -2, -3, -4 }), 0.0001);
    }

    @Test
    @DisplayName("max() handles empty arrays by returning Integer.MIN_VALUE")
    void maxEmptyArray() {
        assertEquals(Integer.MIN_VALUE, stats.max(new int[0]));
    }

    @Test
    @DisplayName("max() handles equal values")
    void maxEqualValues() {
        assertTrue(stats.max(new int[] { 5, 5, 5 }) == 5);
    }
}
