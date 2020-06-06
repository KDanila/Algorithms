package ru.kdv.collinearPoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPointsOp {
    private LineSegment[] ls;

    public BruteCollinearPointsOp(Point[] points) {
        // Corner cases
        if (points == null) throw new NullPointerException();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null) throw new NullPointerException();
            for (int j = i + 1; j < n; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
            }
        }
        Point[] ps = points.clone();
        Arrays.sort(ps);
        List<LineSegment> list = new ArrayList<>();
        // Brute force
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point[] p = new Point[4];
                        p[0] = ps[i];
                        p[1] = ps[j];
                        p[2] = ps[k];
                        p[3] = ps[l];
                        double s1 = p[0].slopeTo(p[1]);
                        double s2 = p[0].slopeTo(p[2]);
                        if (s1 != s2) continue;
                        double s3 = p[0].slopeTo(p[3]);
                        if (s1 == s3) {
                            Arrays.sort(p);
                            list.add(new LineSegment(p[0], p[3]));
                        }
                    }
                }
            }
        }
        // transform to array
        ls = list.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return ls.length;
    }

    public LineSegment[] segments() {
        return ls.clone();
    }
}