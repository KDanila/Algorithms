package main.java.sliderPuzzle.ru.kdv.queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        checkBeforeAddArgument(item);
        items[size++] = item;
        resizedItems();
    }

    private void checkBeforeAddArgument(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("wrong value");
        }
    }

    private void resizedItems() {
        int length = items.length;
        Item[] newItems = null;
        if (length == size) {
            newItems = (Item[]) new Object[length * 2];
        } else if (length / 4 >= size) {
            newItems = (Item[]) new Object[length / 2];
        }
        if (items != newItems && newItems != null) {
            int minLength = Math.min(items.length, newItems.length);
            if (minLength >= 0) System.arraycopy(items, 0, newItems, 0, minLength);
            items = newItems;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        checkEmptyArray();
        int random = StdRandom.uniform(0, size);
        Item toReturn = items[random];
        if (items.length - 1 - random >= 0)
            System.arraycopy(items, random + 1, items, random, items.length - 1 - random);
        size--;
        resizedItems();
        return toReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        checkEmptyArray();
        int random = StdRandom.uniform(0, size);
        return items[random];
    }

    private void checkEmptyArray() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<>();
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {
        private int counter = 0;
        private Item[] iteratorItems = (Item[]) items;

        RandomizedQueueIterator() {
            shuffleItems();
        }

        private void shuffleItems() {
            for (int i = 0; i < size; i++) {
                int random = StdRandom.uniform(0, size);
                Item temp = iteratorItems[i];
                iteratorItems[i] = iteratorItems[random];
                iteratorItems[random] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return counter != size;
        }

        @Override
        public Item next() {
            if (counter > size) {
                throw new NoSuchElementException("iterator next element is null");
            }
            return iteratorItems[counter++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}