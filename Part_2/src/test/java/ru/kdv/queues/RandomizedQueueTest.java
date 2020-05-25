package ru.kdv.queues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {
    RandomizedQueue<String> queue;

    @BeforeEach
    public void init() {
        queue = new RandomizedQueue<>();
    }

    @Test
    public void whenInitShouldReturnIsEmptyTrue() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void whenAddShouldReturnSizeOne() {
        queue.enqueue("One");
        assertEquals(1, queue.size());
    }


}