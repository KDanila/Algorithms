package ru.kdv.collinearPoints;

import java.util.Arrays;

public class FastCollinearPoints {
    private final Point[] points;
    private int numberOfSegments;
    private final int NUMBER_OF_SEGMENT_IN_LINE;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        checkInputPoints(points);
        this.points = Arrays.copyOf(points, points.length);
        this.numberOfSegments = 0;
        this.NUMBER_OF_SEGMENT_IN_LINE = 3;
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        Point one;
        Point two = null;
        double slope = .0;
        LineSegment[] lineSegments = new LineSegment[points.length - 1];
        for (int i = 0; i < points.length; i++) {
            one = points[i];
            Arrays.sort(points, one.slopeOrder());
            slope = one.slopeTo(points[1]);
            for (int j = NUMBER_OF_SEGMENT_IN_LINE; j < points.length; j++) {
                if (slope != one.slopeTo(points[j])) {
                    break;
                }
                two = points[j];
                i = j;
            }
            if (two != null) {
                lineSegments[numberOfSegments++] = new LineSegment(one, two);
                two = null;
            }
        }
        return lineSegments;
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
}
