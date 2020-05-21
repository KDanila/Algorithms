package main.ru.kdv.queues;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node itemFirst;
    private Node itemLast;
    private int size;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }


    // add the item to the front
    public void addFirst(Item item) {
        checkBeforeAddArgument(item);
        resizeArray();
        size++;
    }


    // add the item to the back
    public void addLast(Item item) {
        checkBeforeAddArgument(item);
        resizeArray();
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        checkBeforeRemoveArgument();
        resizeArray();
        size--;
        return null;
    }

    // remove and return the item from the back
    public Item removeLast() {
        checkBeforeRemoveArgument();
        resizeArray();
        size--;
        return null;
    }

    /*
     Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
        Throw an UnsupportedOperationException if the client calls the remove() method in the iterator.
     */
    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Item next() {
                return null;
            }
        };
    }

    /*

       Throw an IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
       Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty.

        */
    private void checkBeforeAddArgument(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("wrong value");
        }
    }

    private void checkBeforeRemoveArgument() {

    }

    private void resizeArray() {
//        if (itemNumber == items.length) {
//            Node[] copy = (Node[]) new Object[items.length * 2];
//            for (int i = 0; i < items.length; i++) {
//                copy[i] = items[i];
//            }
//            items = copy;
//        }
//        if (itemNumber <= items.length / 4) {
//            Node[] copy = (Node[]) new Object[items.length / 2];
//            for (int i = 0; i < items.length; i++) {
//                copy[i] = items[i];
//            }
//            items = copy;
//        }
    }


    // unit testing (required)
    public static void main(String[] args) {

    }

    private class Node {
        Item item;
        Node next;
        Node previous;
    }
}