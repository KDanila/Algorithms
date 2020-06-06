package main.java.sliderPuzzle.kdv.collinearPoints;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.kdv.collinearPoints.FastCollinearPoints;
import ru.kdv.collinearPoints.LineSegment;
import ru.kdv.collinearPoints.Point;

import static org.junit.jupiter.api.Assertions.*;

class FastCollinearPointsTest {

    FastCollinearPoints FastCollinearPointsOneLine;
    FastCollinearPoints FastCollinearPointsOneLineTwo;
    FastCollinearPoints FastCollinearPointsNotOneLine;

    @BeforeEach
    public void init(){
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
        FastCollinearPointsOneLine = new FastCollinearPoints(pointsOneLine);
        FastCollinearPointsOneLineTwo = new FastCollinearPoints(pointsOneLineTwo);
        FastCollinearPointsNotOneLine = new FastCollinearPoints(pointsNotOneLine);
    }

    @Test
    void whenAllPointIsNullShouldThrowException() {
        Point[] pointsWithNullPoints = {null, null, null, null};
        assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(pointsWithNullPoints));
    }

    @Test
    void whenIncomeArrayIsNullShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(null));
    }

    @Test
    void whenAddFastCollinearPointsShouldReturnOneSegment() {
        LineSegment[] segments = FastCollinearPointsOneLine.segments();
        assertEquals(1, FastCollinearPointsOneLine.numberOfSegments());
    }

    @Test
    void whenAddFastCollinearPointsTwoShouldReturnOneSegment() {
        LineSegment[] segments = FastCollinearPointsOneLineTwo.segments();
        assertEquals(1, FastCollinearPointsOneLineTwo.numberOfSegments());
    }


}