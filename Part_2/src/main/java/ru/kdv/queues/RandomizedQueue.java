package ru.kdv.queues;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    Item[] items;
    int size;

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
        size++;
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
        if (items != newItems) {
            for (int i = 0; i < newItems.length; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        return null;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return null;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return null;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}