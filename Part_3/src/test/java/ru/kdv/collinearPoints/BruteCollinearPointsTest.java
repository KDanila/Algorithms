package ru.kdv.collinearPoints;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BruteCollinearPointsTest {
    BruteCollinearPoints bruteCollinearPointsOneLine;
    BruteCollinearPoints bruteCollinearPointsNotOneLine;

    @BeforeEach
    public void init() throws InterruptedException {
        Point[] pointsOneLine = {new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(5, 4)
        };
        Point[] pointsOneLineTwo = {new Point(1, 1),
                new Point(2, 2),
                new Point(3, 7),
                new Point(5, 4)
        };
        bruteCollinearPointsOneLine = new BruteCollinearPoints(pointsOneLine);
        bruteCollinearPointsNotOneLine = new BruteCollinearPoints(pointsOneLineTwo);
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

}