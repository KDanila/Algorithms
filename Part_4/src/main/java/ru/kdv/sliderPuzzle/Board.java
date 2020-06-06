package ru.kdv.sliderPuzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Arrays;

public class Board {
    private final int[][] tiles;
    private final MinPQ queue;


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.queue = new MinPQ(tiles.length * tiles.length - 1);
        this.tiles = Arrays.copyOf(tiles, tiles.length);
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(tiles.length);
        sb.append("\n");
        for (int[] tile : tiles) {
            for (int i : tile) {
                sb.append(i);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return -1;
    }

    // number of tiles out of place
    public int hamming() {
        int wrongPosition = 0;
        int length = tiles.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (tiles[i][j] != length * i + j + 1 && tiles[i][j] != 0) {
                    wrongPosition++;
                }
            }
        }
        return wrongPosition;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int distance = 0;
        int length = tiles.length;
        int x0, y0;
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                if (tiles[y][x] == 0) continue;
                y0 = (tiles[y][x] - 1) / length;
                x0 = (tiles[y][x] - 1) % length;

                y0 = y0 - y < 0 ? (y0 - y) * -1 : (y0 - y);
                x0 = x0 - x < 0 ? (x0 - x) * -1 : (x0 - x);
                distance += y0 + x0;
                //distance += Math.abs(x0 - x) + Math.abs(y0 - y);
                //Restriction "You may not call any library functions other those in java.lang, java.util, and algs4.jar."
            }
        }
        return distance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

}
