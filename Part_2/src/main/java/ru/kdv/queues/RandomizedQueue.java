package ru.kdv.queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

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
            int minLength = items.length < newItems.length ? items.length : newItems.length;
            for (int i = 0; i < minLength; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        int random = StdRandom.uniform(0, size);
        Item toReturn = items[random];
        for (int i = random; i < items.length - 1; i++) {
            items[i] = items[i + 1];
        }
        size--;
        resizedItems();
        return toReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int random = StdRandom.uniform(0, size);
        return items[random];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>();
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
                Item temp = (Item) iteratorItems[i];
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
                throw new UnsupportedOperationException("iterator next element is null");
            }
            return (Item) items[++counter];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}