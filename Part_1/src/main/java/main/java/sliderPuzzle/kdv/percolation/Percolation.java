package main.java.sliderPuzzle.kdv.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] percolationGrid;
    private int openSideNumber;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final int top;
    private final int bottom;
    private final int size;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        check(n);
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 1);
        percolationGrid = new boolean[n][n];
        top = 0;
        bottom = n * n;
        size = n;
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        check(row, col);
        if (isOpen(row, col)) {
            return;
        }
        row--;
        col--;
        percolationGrid[row][col] = true;
        checkAndUnionNearestCell(row, col);
        openSideNumber++;
    }

    private void checkAndUnionNearestCell(int row, int col) {
        int indexOne = transformToWeightedIndex(row + 1, col + 1);
        int indexTwo;
        // union 1st row with top level
        if (row == 0) {
            weightedQuickUnionUF.union(top, indexOne);
        }
        // union last row with bottom level
        if (row == size - 1) {
            weightedQuickUnionUF.union(bottom, indexOne);
        }
        // check up cell
        if (isOpen(row, col + 1)) {
            indexTwo = transformToWeightedIndex(row, col + 1);
            weightedQuickUnionUF.union(indexOne, indexTwo);
        }
        // check left cell
        if (isOpen(row + 1, col)) {
            indexTwo = transformToWeightedIndex(row + 1, col);
            weightedQuickUnionUF.union(indexOne, indexTwo);
        }
        // check right cell
        if (isOpen(row + 1, col + 1 + 1)) {
            indexTwo = transformToWeightedIndex(row + 1, col + 1 + 1);
            weightedQuickUnionUF.union(indexOne, indexTwo);
        }
        // check bottom cell
        if (isOpen(row + 1 + 1, col + 1)) {
            indexTwo = transformToWeightedIndex(row + 1 + 1, col + 1);
            weightedQuickUnionUF.union(indexOne, indexTwo);
        }
    }

    private int transformToWeightedIndex(int row, int col) {
        return ((row) * size) - size + (col) - 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row--;
        col--;
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            return false;
        }
        return percolationGrid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        int cellOne = transformToWeightedIndex(row, col);
        return weightedQuickUnionUF.connected(top, cellOne);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSideNumber;
    }

    // does the system percolate?
    public boolean percolates() {

        return weightedQuickUnionUF.connected(top, bottom);
    }

    private void check(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n is out of range");
        }
    }

    private void check(int row, int col) {
        if (row <= 0 || row > percolationGrid.length) {
            throw new IllegalArgumentException("Row is out of range");
        }
        if (col <= 0 || col > percolationGrid.length) {
            throw new IllegalArgumentException("Column is out of range");
        }
    }

    public void setPercolationGrid(boolean[][] percolationGrid) {
        this.percolationGrid = percolationGrid;
    }

    public boolean[][] getPercolationGrid() {
        return this.percolationGrid;
    }
}
