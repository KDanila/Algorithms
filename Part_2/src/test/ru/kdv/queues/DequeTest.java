package test.ru.kdv.queues;

import main.ru.kdv.queues.Deque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {
    Deque<String> deque;

    @BeforeEach
    public void init() {
        deque = new Deque<>();
    }

    @Test
    public void whenInitShouldReturnIsEmptyTrue() {
        assertTrue(deque.isEmpty());
    }

    @Test
    public void ifRemoveArgumentInEmptyDequeShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> deque.removeFirst());
        assertThrows(IllegalArgumentException.class, () -> deque.removeLast());
    }

    @Test
    void whenAddFirstShouldReturnSizeOneAndFirstEqualsLast() {
        deque.addFirst("First");
        assertEquals(1, deque.size());
        assertSame(deque.getFirstNode(), deque.getLastNode());
    }

    @Test
    void whenAddSecondShouldReturnSizeTwoAndFirstDontEqualsLast() {
        deque.addFirst("First");
        deque.addFirst("Second");
        assertEquals(2, deque.size());
        assertNotSame(deque.getFirstNode(), deque.getLastNode());
        assertEquals("Second", deque.getFirstNode().getItem());
        assertEquals("First", deque.getLastNode().getItem());
    }

    @Test
    void whenAddLastShouldReturnSizeOneAndFirstEqualsLast() {
        deque.addLast("First");
        assertEquals(1, deque.size());
        assertSame(deque.getFirstNode(), deque.getLastNode());
    }

    @Test
    void whenAddLastShouldReturnSizeTwoAndFirstDontEqualsLast() {
        deque.addLast("First");
        deque.addLast("Second");
        assertEquals(2, deque.size());
        assertNotSame(deque.getFirstNode(), deque.getLastNode());
        assertEquals("First", deque.getFirstNode().getItem());
        assertEquals("Second", deque.getLastNode().getItem());
    }

    @Test
    void whenRemoveFirstShouldReturnSizeZero() {
        deque.addFirst("First");
        deque.addFirst("Second");
        deque.removeFirst();
        assertEquals("First", deque.getFirstNode().getItem());
        deque.removeFirst();
        assertEquals(0, deque.size());
    }

    @Test
    void whenUseIteratorShouldReturnCorrectArray() {
        deque.addFirst("First");
        deque.addFirst("Second");
        deque.addLast("Third");
        deque.addLast("Fourth");
        Iterator<String> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Fourth", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Third", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("First", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Second", iterator.next());
        assertFalse(iterator.hasNext());
    }


}