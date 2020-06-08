package ru.kdv.sliderPuzzle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board2x2;
    private Board board3x3;
    private Board board3x3copy;
    private Board board3x3unsorted;
    private Board board3x3unsortedRightBottomCorner;
    private Board board3x3unsortedLeftUpCorner;
    private Board board3x3unsortedLeftMiddle;

    @BeforeEach
    void setUp() {
        int[][] twoSize = {{1, 2}, {3, 0}};
        int[][] threeSize = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] threeSizeUnsorted = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        int[][] threeSizeUnsorted2 = {{8, 1, 3}, {4, 2, 5}, {7, 6, 0}};
        int[][] threeSizeUnsorted3 = {{0, 1, 3}, {4, 2, 5}, {7, 6, 8}};
        int[][] threeSizeUnsorted4 = {{4, 1, 3}, {0, 2, 5}, {7, 6, 8}};

        board2x2 = new Board(twoSize);
        board3x3 = new Board(threeSize);
        board3x3copy = new Board(threeSize);
        board3x3unsorted = new Board(threeSizeUnsorted);
        board3x3unsortedRightBottomCorner = new Board(threeSizeUnsorted2);
        board3x3unsortedLeftUpCorner = new Board(threeSizeUnsorted3);
        board3x3unsortedLeftMiddle = new Board(threeSizeUnsorted4);
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
    void whenUnsortedArrayNeighborsShouldAddFourNeighbors() {
        int count = 0;
        for (Board neighbor : board3x3unsorted.neighbors()) {
            count++;
        }
        assertEquals(4, count);
    }
    @Test
    void whenUnsortedArrayRightBottomCornerNeighborsShouldAddTwoNeighbors() {
        int count = 0;
        for (Board neighbor : board3x3unsortedRightBottomCorner.neighbors()) {
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    void whenUnsortedArrayLeftUpNeighborsShouldAddTwoNeighbors() {
        int count = 0;
        for (Board neighbor : board3x3unsortedLeftUpCorner.neighbors()) {
            count++;
        }
        assertEquals(2, count);
    }
    @Test
    void whenUnsortedArrayLeftMiddleNeighborsShouldAddTwoNeighbors() {
        int count = 0;
        for (Board neighbor : board3x3unsortedLeftMiddle.neighbors()) {
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    void whenBoardsSameShouldReturnEqual() {
        assertTrue(board3x3.equals(board3x3));
    }

    @Test
    void whenBoardsCopyOfEachOtherShouldReturnEqual() {
        assertTrue(board3x3.equals(board3x3copy));
    }

    @Test
    void whenBoardsIsNotSameShouldReturnNotEqual() {
        assertFalse(board3x3.equals(board3x3unsorted));
    }

    @Test
    void twin() {
    }
}