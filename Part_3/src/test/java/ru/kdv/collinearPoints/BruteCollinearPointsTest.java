package ru.kdv.collinearPoints;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BruteCollinearPointsTest {
    BruteCollinearPoints bruteCollinearPointsOneLine;
    BruteCollinearPoints bruteCollinearPointsOneLineTwo;
    BruteCollinearPoints bruteCollinearPointsNotOneLine;

    @BeforeEach
    public void init() throws InterruptedException {
        Point[] pointsOneLine = {new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(5, 5)
        };
        Point[] pointsOneLineTwo = {new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(3, 7),
                new Point(5, 5)
        };
        Point[] pointsNotOneLine = {new Point(1, 1),
                new Point(2, 2),
                new Point(3, 7),
                new Point(5, 4)
        };
        bruteCollinearPointsOneLine = new BruteCollinearPoints(pointsOneLine);
        bruteCollinearPointsOneLineTwo = new BruteCollinearPoints(pointsOneLineTwo);
        bruteCollinearPointsNotOneLine = new BruteCollinearPoints(pointsNotOneLine);
    }

    @Test
    void whenAllPointIsNullShouldThrowException() {
        Point[] pointsWithNullPoints = {null, null, null, null};
        assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(pointsWithNullPoints));
    }

    @Test
    void whenIncomeArrayIsNullShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(null));
    }

    @Test
    void whenAddBruteCollinearPointsShouldReturnOneSegment() {
        LineSegment[] segments = bruteCollinearPointsOneLine.segments();
        assertEquals(1, bruteCollinearPointsOneLine.numberOfSegments());
    }

    @Test
    void whenAddBruteCollinearPointsTwoShouldReturnOneSegment() {
        LineSegment[] segments = bruteCollinearPointsOneLineTwo.segments();
        assertEquals(1, bruteCollinearPointsOneLineTwo.numberOfSegments());
    }

}