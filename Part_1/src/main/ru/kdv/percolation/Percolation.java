package ru.kdv.percolation;

public class Percolation {
    int[][] percolationGrid;
    int[][] percolarionFullGrid;
    int openSideNumber;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                percolationGrid[i][j] = 0;
                percolarionFullGrid[i][j] = 0;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        check(row, col);
        percolationGrid[row][col] = 1;
        openSideNumber++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return percolationGrid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return percolarionFullGrid[row][col] == 1;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSideNumber;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    private void check(int row, int col) {
        if (row <= 0 || row > percolationGrid.length) {
            throw new IllegalArgumentException("Row is out of range");
        }
        if (col <= 0 || col > percolationGrid.length) {
            throw new IllegalArgumentException("Column is out of range");
        }
    }
}
