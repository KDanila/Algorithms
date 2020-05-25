package ru.kdv.percolation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PercolationTest {

    Percolation percolation;

    @BeforeEach
    public void init() {
        percolation = new Percolation(5);
    }

    @Test
    public void ifCloseShouldOpen() {
        boolean[][] initArray = percolation.getPercolationGrid();
        percolation.open(1, 1);
        assertTrue(percolation.isOpen(1, 1));
        assertFalse(percolation.isOpen(1, 2));
    }

    @Test
    public void ifOutOfIndexShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> percolation.open(-1, 1));
    }

    @Test
    public void ifSystemOpenShouldReturnPerlocateTrue() {
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1);
        percolation.open(5, 1);
        assertTrue(percolation.percolates());
    }

    @Test
    public void ifSystemCloseShouldReturnPerlocateFalse() {
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 2);
        percolation.open(4, 1);
        percolation.open(5, 1);
        assertFalse(percolation.percolates());
    }

    @Test
    public void ifCellFullShouldReturnIsFullTrue() {
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(2, 2);
        assertTrue(percolation.isFull(2,2));
    }

    @Test
    public void ifCellNotFullShouldReturnIsFullFalse() {
        percolation.open(1, 1);
        percolation.open(5, 1);
        percolation.open(2, 2);
        assertFalse(percolation.isFull(2,2));
    }

    }