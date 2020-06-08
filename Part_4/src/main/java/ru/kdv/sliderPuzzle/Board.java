package ru.kdv.sliderPuzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final int[][] tiles;
    private int xE;
    private int yE;


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles.clone();
        findEmptyBlock();
    }

    private void findEmptyBlock() {
        boolean zeroFind = false;
        for (int i = 0; i < tiles.length && !zeroFind; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] == 0) {
                    yE = i;
                    xE = j;
                    zeroFind = true;
                    break;
                }
            }
        }
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
        int temp = -1;
        if (xE - 1 >= 0) {
            int[][] A = copyBoard(tiles);
            temp = A[yE][xE];
            A[yE][xE] = A[yE][xE - 1];
            A[yE][xE - 1] = temp;
            neighbors.add(new Board(A));
        }
        if (xE + 1 < tiles.length) {
            int[][] B = copyBoard(tiles);
            temp = B[yE][xE];
            B[yE][xE] = B[yE][xE + 1];
            B[yE][xE + 1] = temp;
            neighbors.add(new Board(B));
        }
        if (yE - 1 >= 0) {
            int[][] C = copyBoard(tiles);
            temp = C[yE][xE];
            C[yE][xE] = C[yE - 1][xE];
            C[yE - 1][xE] = temp;
            neighbors.add(new Board(C));
        }
        if (yE + 1 < tiles.length) {
            int[][] D = copyBoard(tiles);
            temp = D[yE][xE];
            D[yE][xE] = D[yE + 1][xE];
            D[yE + 1][xE] = temp;
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
        int[][] copy = copyBoard(tiles);
        if (yE != 0) {
            int temp = copy[0][0];
            copy[0][0] = copy[0][1];
            copy[0][1] = temp;
        } else {
            int temp = copy[1][0];
            copy[1][0] = copy[1][1];
            copy[1][1] = temp;
        }
        return new Board(copy);
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }
}
