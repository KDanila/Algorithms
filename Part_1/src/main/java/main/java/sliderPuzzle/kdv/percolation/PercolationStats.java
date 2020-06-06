package main.java.sliderPuzzle.kdv.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private Percolation percolation;
    private final int trials;
    private final double[] percentageTrials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int tr) {
        checkParameters(n, tr);
        this.trials = tr;
        percentageTrials = new double[tr];
        while (tr != 0) {
            percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }
            double percentage = (double) percolation.numberOfOpenSites() / (n * n);
            percentageTrials[--tr] = percentage;
        }
    }

    private void checkParameters(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Parameters can not be negative");
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percentageTrials);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percentageTrials);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.printf("mean\t\t\t\t = %f\n", percolationStats.mean());
        System.out.printf("stddev\t\t\t\t = %f\n", percolationStats.stddev());
        System.out.printf("95%% confidence interval\t\t\t\t = [%f, %f]\n", percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}
