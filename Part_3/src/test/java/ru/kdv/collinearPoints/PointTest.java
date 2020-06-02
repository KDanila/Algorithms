package ru.kdv.collinearPoints;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    Point a, b, c, d, e, f, g, j;

    @BeforeEach
    public void init() throws InterruptedException {
        a = new Point(1, 1);
        b = new Point(2, 2);
        c = new Point(10, 1);
        d = new Point(1, 10);
        e = new Point(226, 418);
        f = new Point(119, 418);
        g = new Point(8, 3);
        j = new Point(3, 5);
    }

    @Test
    void slopeToIfVerticalShouldReturnPositiveInfinity() {
        assertEquals(Double.POSITIVE_INFINITY, a.slopeTo(d));
    }

    @Test
    void slopeToIfVerticalShouldReturnZero() {
        assertEquals(0.0, a.slopeTo(c));
    }

    @Test
    void slopePointsEFShouldReturnPositiveZero() {
        assertEquals(0.0, e.slopeTo(f));
    }

    @Test
    void slopeToSamePointShouldReturnNegativeInfinity() {
        assertEquals(Double.NEGATIVE_INFINITY, a.slopeTo(a));
    }

    @Test
    void compareToShouldReturnNegativeNumberWhenY0lessThenY1() {
        assertTrue(a.compareTo(b) < 0);
    }

    @Test
    void compareToShouldReturnZeroForTheIdenticalPoint() {
        assertTrue(a.compareTo(a) == 0);
    }

    @Test
    void compareToGJShouldReturnCorrectValue() {
        assertTrue(g.compareTo(j) == 1);
    }

    @Test
    void compareToShouldReturnNegativeNumberWhenY0EqualY1AndX0lessThenX1() {
        assertTrue(a.compareTo(d) < 0);
    }
}