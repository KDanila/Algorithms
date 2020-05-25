package test.ru.kdv.queues;

import main.ru.kdv.queues.Deque;
import main.ru.kdv.queues.RandomizedQueue;
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