package main.java.sliderPuzzle.kdv.queues;

import lombok.Data;
import lombok.Getter;

import java.util.Iterator;
import java.util.NoSuchElementException;

@Data
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> firstNode;
    private Node<Item> lastNode;
    private int size;

    // construct an empty deque
    public Deque() {
        firstNode = lastNode = null;
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
        if (size == 0) {
            firstNode = lastNode = new Node<>(item);
        } else {
            Node<Item> firstOld = firstNode;
            firstNode = new Node<>(item);
            firstNode.previous = firstOld;
            firstOld.next = firstNode;
        }
        size++;
    }


    // add the item to the back
    public void addLast(Item item) {
        checkBeforeAddArgument(item);
        if (size == 0) {
            firstNode = lastNode = new Node<>(item);
        } else {
            Node<Item> lastOld = lastNode;
            lastNode = new Node<>(item);
            lastNode.next = lastOld;
            lastOld.previous = lastNode;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        checkBeforeRemoveArgument();
        Item item = firstNode.item;
        if (size == 1) {
            firstNode = lastNode = null;
        } else {
            firstNode = firstNode.previous;
            firstNode.next = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        checkBeforeRemoveArgument();
        Item item = lastNode.item;
        if (size == 1) {
            lastNode = firstNode = null;
        } else {
            lastNode = lastNode.next;
            lastNode.previous = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> currentNode = lastNode;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public Item next() {
                if (currentNode == null) {
                    throw new NoSuchElementException("iterator next element is null");
                }
                Item item = currentNode.item;
                currentNode = currentNode.next;
                return item;
            }
        };
    }

    private void checkBeforeAddArgument(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("wrong value");
        }
    }

    private void checkBeforeRemoveArgument() {
        if (size == 0) {
            throw new NoSuchElementException("Deque is empty");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

    @Getter
    public static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;

        public Node(Item item) {
            this.item = item;
        }
    }
}