package ru.kdv.collinearPoints;

import java.util.Arrays;

public class BruteCollinearPoints {
    private final Point[] points;
    private int numberOfSegments;
    private final int NUMBER_OF_SEGMENT_IN_LINE;
    private final LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        checkInputPoints(points);
        this.points = Arrays.copyOf(points, points.length);
        Arrays.sort(points);
        this.numberOfSegments = 0;
        this.NUMBER_OF_SEGMENT_IN_LINE = 3;
        segments = new LineSegment[this.points.length - 1];
        findSegments();
    }

    private void checkInputPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points = null");
        }
        int nullPoints = 0;
        for (Point point : points) {
            if (point != null) {
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
        return this.numberOfSegments;
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
     */

    public LineSegment[] segments() {
        return segments;
    }

    private void findSegments() {
        Point pointOne;
        Point pointTwo = points[0];
        double slope = 0.0;
        int sameSlopePointCounter = 0;
        double[] slopeArray = new double[points.length];
        initSlopeArray(slopeArray);
        for (int i = 0; i < points.length; i++) {
            pointOne = points[i];
            for (int j = i + 1; j < points.length; j++) {
                if (j == i + 1) {
                    pointTwo = points[j];
                    slope = pointOne.slopeTo(pointTwo);
                    sameSlopePointCounter++;
                    continue;
                }
                if (slope == pointOne.slopeTo(points[j])) {
                    //add biggest last point
                    if (pointTwo.compareTo(points[j]) < 0) {
                        pointTwo = points[j];
                    }
                    sameSlopePointCounter++;
                }
            }
            if (sameSlopePointCounter >= NUMBER_OF_SEGMENT_IN_LINE) {
                if (checkSameSlope(slopeArray, pointOne.slopeTo(pointTwo))) {
                    addInSlopeArray(slopeArray, pointOne.slopeTo(pointTwo));
                    segments[numberOfSegments++] = new LineSegment(pointOne, pointTwo);
                }
            }
            sameSlopePointCounter = 0;
        }
    }

    private void initSlopeArray(double[] slopeArray) {
        Arrays.fill(slopeArray, Double.NEGATIVE_INFINITY);
    }

    private void addInSlopeArray(double[] slopeArray, double slope) {
        for (int i = 0; i < slopeArray.length; i++) {
            if (slopeArray[i] == slope) {
                break;
            }
            if (slopeArray[i] == Double.NEGATIVE_INFINITY) {
                slopeArray[i] = slope;
                break;
            }
        }
    }

    private boolean checkSameSlope(double[] slopeArray, double slope) {
        if (slopeArray[0] == Double.NEGATIVE_INFINITY) {
            return true;
        }
        for (double v : slopeArray) {
            if (v == slope) {
                return false;
            }
        }
        return true;
    }
}
