package ru.kdv.sliderPuzzle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board2x2;
    private Board board3x3;
    private Board board3x3unsorted;

    @BeforeEach
    void setUp() {
        int[][] twoSize = {{1, 2}, {3, 0}};
        int[][] threeSize = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] threeSizeUnsorted = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};

        board2x2 = new Board(twoSize);
        board3x3 = new Board(threeSize);
        board3x3unsorted = new Board(threeSizeUnsorted);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void when2x2BoardShouldCorrectlyShowToString() {
        System.out.println(board2x2.toString());
    }

    @Test
    void when3x3BoardShouldCorrectlyShowToString() {
        System.out.println(board3x3.toString());
    }

    @Test
    void dimension() {
    }

    @Test
    void whenCheckHammingDistanceWithSortedArrayShouldReturnZero() {
        assertEquals(0, board3x3.hamming());
    }

    @Test
    void whenCheckHammingDistanceWithUnsortedArrayShouldReturnFive() {
        assertEquals(5, board3x3unsorted.hamming());
    }

    @Test
    void whenCheckManhattanDistanceWithSortedArrayShouldReturnZero() {
        assertEquals(0, board3x3.manhattan());
    }

    @Test
    void whenCheckManhattanDistanceWithUnsortedArrayShouldReturnZero() {
        assertEquals(10, board3x3unsorted.manhattan());
    }

    @Test
    void whenBoardIsSortedIsGoalReturnTrue() {
        assertTrue(board3x3.isGoal());
    }

    @Test
    void whenBoardIsUnSortedIsGoalReturnFalse() {
        assertFalse(board3x3unsorted.isGoal());
    }

    @Test
    void testEquals() {
    }

    @Test
    void neighbors() {
    }

    @Test
    void twin() {
    }
}