package ru.kdv.kd_trees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {

    private final Set<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (!points.contains(p)) {
            points.add(p);
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        List<Point2D> toReturn = new ArrayList<>();
        for (Point2D point : points) {
            if (rect.contains(point)) {
                toReturn.add(point);
            }
        }
        return toReturn;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        Point2D nearestPoint = null;
        double minDistance = -1.0;
        double distance;
        for (Point2D point : points) {
            distance = point.distanceTo(p);
            if (Double.compare(minDistance, -1.0) == 0
                    && Double.compare(distance, minDistance) < 0) {
                minDistance = distance;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        //no need
    }
}
