package ru.kdv.sliderPuzzle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    private Solver board2x2;
    private Solver board3x3;
    private Solver board3x3copy;
    private Solver board3x3unsorted;
    private Solver board3x3unsortedRightBottomCorner;
    private Solver board3x3unsortedLeftUpCorner;
    private Solver board3x3unsortedLeftMiddle;

    @BeforeEach
    void setUp() {
        int[][] twoSize = {{1, 2}, {3, 0}};
        int[][] threeSize = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] threeSizeUnsorted = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        int[][] threeSizeUnsorted2 = {{8, 1, 3}, {4, 2, 5}, {7, 6, 0}};
        int[][] threeSizeUnsorted3 = {{0, 1, 3}, {4, 2, 5}, {7, 6, 8}};
        int[][] threeSizeUnsorted4 = {{4, 1, 3}, {0, 2, 5}, {7, 6, 8}};

        board2x2 = new Solver(new Board(twoSize));
        board3x3 = new Solver(new Board(threeSize));
        board3x3copy = new Solver(new Board(threeSize));
        board3x3unsorted = new Solver(new Board(threeSizeUnsorted));
        board3x3unsortedRightBottomCorner = new Solver(new Board(threeSizeUnsorted2));
        board3x3unsortedLeftUpCorner = new Solver(new Board(threeSizeUnsorted3));
        board3x3unsortedLeftMiddle = new Solver(new Board(threeSizeUnsorted4));
    }

    @Test
    void whenIs3x3SortedShouldReturnIsSolvableTrue() {
        assertTrue(board3x3.isSolvable());

    }
    @Test
    void whenIs3x3UnSortedShouldReturnIsSolvableTrue() {
        assertTrue(board3x3unsorted.isSolvable());
        board3x3unsorted.solution().forEach(System.out::println);
    }

    @Test
    void moves() {
    }

    @Test
    void solution() {
    }

    @Test
    void main() {
    }
}