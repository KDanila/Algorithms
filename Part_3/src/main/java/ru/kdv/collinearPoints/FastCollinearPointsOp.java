package ru.kdv.collinearPoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPointsOp {
    private LineSegment[] ls;

    public FastCollinearPointsOp(Point[] points) {
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

        for (int i = 0; i < n; i++) {
            Point[] p = ps.clone();
            Arrays.sort(p, p[i].slopeOrder());
            int j = 1;
            while (j < n - 2) {
                int k = j;
                double s1 = p[0].slopeTo(p[k++]);
                double s2;
                do {
                    if (k == n) {
                        k++;
                        break;
                    }
                    s2 = p[0].slopeTo(p[k++]);
                } while (s1 == s2);
                if (k - j < 4) {
                    j++;
                    continue;
                }
                int len = k-- - j;
                Point[] line = new Point[len];
                line[0] = p[0];

                System.arraycopy(p, j + 1 - 1, line, 1, len - 1);
                Arrays.sort(line);
                // remove duplicate
                if (line[0] == p[0]) {
                    list.add(new LineSegment(line[0], line[len - 1]));
                }
                j = k;
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