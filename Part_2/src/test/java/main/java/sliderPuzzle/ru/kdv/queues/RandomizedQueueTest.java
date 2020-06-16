package main.java.sliderPuzzle.ru.kdv.queues;

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

    @Test
    public void whenRemoveShouldReturnCorrectSize() {
        queue.enqueue("One");
        queue.enqueue("Two");
        String s = queue.dequeue();
        assertEquals(1, queue.size());
    }

    @Test
    public void whenUseSampleShouldReturnCorrectSize() {
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.enqueue("7");
        queue.enqueue("8");
        queue.enqueue("9");
        queue.enqueue("10");

        for (String s : queue) {
            System.out.println(s);
        }
    }
}