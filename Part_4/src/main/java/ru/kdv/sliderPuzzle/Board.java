package ru.kdv.sliderPuzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final int[][] tiles;
    private final MinPQ queue;


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.queue = new MinPQ(tiles.length * tiles.length - 1);
        this.tiles = tiles.clone();
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.equals(tiles, board.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();

        int x = 0;
        int y = 0;
        boolean zeroFind = false;
        for (int i = 0; i < tiles.length && !zeroFind; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] == 0) {
                    y = i;
                    x = j;
                    zeroFind = true;
                    break;
                }
            }
        }
        int temp = -1;
        if (x - 1 >= 0) {
            int[][] A = copyBoard(tiles);
            temp = A[y][x];
            A[y][x] = A[y][x - 1];
            A[y][x - 1] = temp;
            neighbors.add(new Board(A));
        }
        if (x + 1 < tiles.length) {
            int[][] B = copyBoard(tiles);
            temp = B[y][x];
            B[y][x] = B[y][x + 1];
            B[y][x + 1] = temp;
            neighbors.add(new Board(B));
        }
        if (y - 1 >= 0) {
            int[][] C = copyBoard(tiles);
            temp = C[y][x];
            C[y][x] = C[y - 1][x];
            C[y - 1][x] = temp;
            neighbors.add(new Board(C));
        }
        if (y + 1 < tiles.length) {
            int[][] D = copyBoard(tiles);
            temp = D[y][x];
            D[y][x] = D[y + 1][x];
            D[y + 1][x] = temp;
            neighbors.add(new Board(D));
        }
        return neighbors;
    }

    private int[][] copyBoard(int[][] copy) {
        int[][] clone = new int[copy.length][];
        for (int row = 0; row < copy.length; row++) {
            clone[row] = copy[row].clone();
        }
        return clone;
    }
    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }
}
