package ru.kdv.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] percolationGrid;
    private int openSideNumber;
    WeightedQuickUnionUF weightedQuickUnionUF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        check(n);
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n);
        percolationGrid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                percolationGrid[i][j] = 0;
            }
        }
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) throws IllegalArgumentException {
        check(row, col);
        if (isOpen(row, col)) {
            return;
        }
        row--;
        col--;
        percolationGrid[row][col] = 1;
        checkAndUnionNearestCell(row, col);
        openSideNumber++;
    }

    private void checkAndUnionNearestCell(int row, int col) {
        int indexOne = transformToWeightedIndex(row + 1, col + 1);
        int indexTwo;
        //check up cell
        if (isOpen(row, col + 1)) {
            indexTwo = transformToWeightedIndex(row, col + 1);
            weightedQuickUnionUF.union(indexOne, indexTwo);
        }
        //check left cell
        if (isOpen(row + 1, col)) {
            indexTwo = transformToWeightedIndex(row + 1, col);
            weightedQuickUnionUF.union(indexOne, indexTwo);
        }
        //check right cell
        if (isOpen(row + 1, col + 1 + 1)) {
            indexTwo = transformToWeightedIndex(row + 1, col + 1 + 1);
            weightedQuickUnionUF.union(indexOne, indexTwo);
        }
        //check bottom cell
        if (isOpen(row + 1 + 1, col + 1)) {
            indexTwo = transformToWeightedIndex(row + 1 + 1, col + 1);
            weightedQuickUnionUF.union(indexOne, indexTwo);
        }
    }

    private int transformToWeightedIndex(int row, int col) {
        return ((row) * percolationGrid.length) - percolationGrid.length + (col) - 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row--;
        col--;
        if (row < 0 || row > percolationGrid.length - 1 || col < 0 || col > percolationGrid.length - 1) {
            return false;
        }
        return percolationGrid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        int cellOne = transformToWeightedIndex(row, col);
        for (int i = 1; i <= percolationGrid[0].length; i++) {
            int cellTwo = transformToWeightedIndex(1, i);
            if (isOpen(1, i) && (weightedQuickUnionUF.find(cellOne) == weightedQuickUnionUF.find(cellTwo))) {
                return true;
            }
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSideNumber;
    }

    // does the system percolate?
    public boolean percolates() {
        int size = percolationGrid[0].length;
        for (int i = 1; i <= size; i++) {
            int cellOne = transformToWeightedIndex(1, i);
            for (int j = 1; j <= size; j++) {
                int cellTwo = transformToWeightedIndex(size, j);
                if (isOpen(1, i) && isOpen(size, j) && (weightedQuickUnionUF.find(cellOne) == weightedQuickUnionUF.find(cellTwo))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void check(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n is out of range");
        }
    }

    private void check(int row, int col) throws IllegalArgumentException {
        if (row <= 0 || row > percolationGrid.length) {
            throw new IllegalArgumentException("Row is out of range");
        }
        if (col <= 0 || col > percolationGrid.length) {
            throw new IllegalArgumentException("Column is out of range");
        }
    }

    public void setPercolationGrid(int[][] percolationGrid) {
        this.percolationGrid = percolationGrid;
    }

    public int[][] getPercolationGrid() {
        return this.percolationGrid;
    }
}
