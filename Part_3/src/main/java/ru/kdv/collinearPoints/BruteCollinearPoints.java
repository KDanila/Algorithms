package ru.kdv.collinearPoints;

public class BruteCollinearPoints {
    private final Point[] points;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        checkInputPoints(points);
        this.points = points;
    }

    private void checkInputPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points = null");
        }
        int nullPoints = 0;
        for (int i = 0; i < points.length; i++) {
            if (points[i] != null) {
                break;
            }
            nullPoints++;
            if (nullPoints == points.length) {
                throw new IllegalArgumentException("All points are null");
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return -1;
    }

    /**
     * The method segments() should include each line segment containing 4 points exactly once.
     * If 4 points appear on a line segment in the order p→q→r→s, then you should include either the line segment p→s
     * or s→p (but not both) and you should not include subsegments such as p→r or q→r. For simplicity, we will not
     * supply any input to BruteCollinearPoints that has 5 or more collinear points.
     * <p>
     * Corner cases. Throw an IllegalArgumentException if the argument to the constructor is null, if any point in the
     * array is null, or if the argument to the constructor contains a repeated point.
     * <p>
     * Performance requirement. The order of growth of the running time of your program should be n4 in the worst case
     * and it should use space proportional to n plus the number of line segments returned.
     *
     * @return
     */

    public LineSegment[] segments() {
        Point pointOne;
        Point pointTemp;
        double slope = .0;
        int sameSlopePointCounter = 0;
        for (int i = 0; i < points.length; i++) {
            pointOne = points[i];
            for (int j = 0; j < points.length - 1; j++) {
                pointTemp = points[j];
                if (j == 0) {
                    slope = pointOne.slopeTo(pointTemp);
                    break;
                }
                if (slope == pointOne.slopeTo(pointTemp)) {
                    sameSlopePointCounter++;
                }
            }


            sameSlopePointCounter = 0;
        }
        return null;
    }
}
