package ru.kdv.kd_trees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class KdTree {
    private int size;
    private Node root;

    private class Node {
        boolean isVertical;
        private final Point2D point;
        private final RectHV rectangle;
        private Node left;
        private Node right;

        public Node(Point2D point, RectHV rectangle, boolean isVertical) {
            this.point = point;
            this.rectangle = rectangle;
            this.isVertical = isVertical;
        }

        boolean nextSeparator() {
            return !isVertical;
        }

        public RectHV rectLB() {
            return isVertical
                    ? new RectHV(rectangle.xmin(), rectangle.ymin(), point.x(), rectangle.ymax())
                    : new RectHV(rectangle.xmin(), rectangle.ymin(), rectangle.xmax(), point.y());
        }

        public RectHV rectRT() {
            return isVertical
                    ? new RectHV(point.x(), rectangle.ymin(), rectangle.xmax(), rectangle.ymax())
                    : new RectHV(rectangle.xmin(), point.y(), rectangle.xmax(), rectangle.ymax());
        }

        public boolean isRightOrTopOf(Point2D q) {
            return (!isVertical && point.y() > q.y())
                    || (isVertical && point.x() > q.x());
        }

    }

    // construct an empty set of points
    public KdTree() {
        size = 0;
        root = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        Objects.requireNonNull(p);
        if (root == null) {
            root = new Node(p, new RectHV(0, 0, 1, 1), true);
            size++;
            return;
        }

        // find node position for insertion
        Node prev = null;
        Node curr = root;
        do {
            if (curr.point.equals(p)) {
                return;
            }
            prev = curr;
            curr = curr.isRightOrTopOf(p) ? curr.left : curr.right;
        } while (curr != null);

        // prepare new node and insert
        if (prev.isRightOrTopOf(p)) {
            prev.left = new Node(p, prev.rectLB(), prev.nextSeparator());
        } else {
            prev.right = new Node(p, prev.rectRT(), prev.nextSeparator());
        }
        size++;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        Objects.requireNonNull(p);
        Node node = root;
        while (node != null) {
            if (node.point.equals(p)) {
                return true;
            }
            node = node.isRightOrTopOf(p) ? node.left : node.right;
        }
        return false;
    }

    // draw all points to standard draw
    public void draw() {
        range(new RectHV(0, 0, 1, 1)).forEach(point -> point.draw());
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        Objects.requireNonNull(rect);
        if (root == null) {
            return Collections.emptyList();
        }
        List<Point2D> toReturn = new ArrayList<>();
        addRecursively(root, rect, toReturn);
        return toReturn;
    }

    private void addRecursively(Node node, RectHV rect, List<Point2D> toReturn) {
        if (node == null) {
            return;
        }
        if (rect.contains(node.point)) {
            toReturn.add(node.point);
            addRecursively(node.left, rect, toReturn);
            addRecursively(node.right, rect, toReturn);
            return;
        }
        if (node.isRightOrTopOf(new Point2D(rect.xmin(), rect.ymin()))) {
            addRecursively(node.left, rect, toReturn);
        }
        if (!node.isRightOrTopOf(new Point2D(rect.xmax(), rect.ymax()))) {
            addRecursively(node.right, rect, toReturn);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        Objects.requireNonNull(p);
        return isEmpty() ? null : nearest(p, root.point, root);
    }

    private Point2D nearest(Point2D target, Point2D closest, Node node) {
        if (node == null) {
            return closest;
        }

        double closestDist = closest.distanceSquaredTo(target);
        if (node.rectangle.distanceSquaredTo(target) < closestDist) {
            double nodeDist = node.point.distanceSquaredTo(target);
            if (nodeDist < closestDist) {
                closest = node.point;
            }
            if (node.isRightOrTopOf(target)) {
                closest = nearest(target, closest, node.left);
                closest = nearest(target, closest, node.right);
            } else {
                closest = nearest(target, closest, node.right);
                closest = nearest(target, closest, node.left);
            }
        }
        return closest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        //1
    }
}
