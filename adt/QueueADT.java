// FILE: adt/QueueADT.java
package BooksApp.adt;

// Implementation of a generic queue
public class QueueADT<E> implements AbstractQueue<E> {
    // Inner class to represent a node in the queue
    private class Node<E> {
        private E element; // The element stored in the node
        private Node<E> next; // The next node in the queue

        // Constructor to initialize the node with an element
        private Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    private Node<E> head; // The head of the queue
    private int size; // The number of elements in the queue

    // Constructor to initialize the queue
    public QueueADT() {
        this.head = null;
        this.size = 0;
    }

    // Inserts the specified element into the queue
    @Override
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.isEmpty()) {
            this.head = newNode;
        } else {
            Node<E> tempNode = this.head;
            while (tempNode.next != null) {
                tempNode = tempNode.next;
            }
            tempNode.next = newNode;
        }

        this.size++;
    }

    // Retrieves and removes the head of this queue, or returns null if this queue is empty
    @Override
    public E poll() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Queue is currently empty.");
        }

        E oldElement = this.head.element;

        Node<E> tempNode = head;
        this.head = this.head.next;
        tempNode.next = null;
        this.size--;
        return oldElement;
    }

    // Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty
    @Override
    public E peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Queue is currently empty.");
        }

        return this.head.element;
    }

    // Returns the number of elements in this queue
    @Override
    public int size() {
        return this.size;
    }

    // Returns true if this queue contains no elements
    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    // Returns a string representation of the queue
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> tempNode = head;

        while (tempNode != null) {
            result.append(tempNode.element);

            if (tempNode.next != null) {
                result.append(", ");
            }

            tempNode = tempNode.next;
        }

        result.append("]");
        return result.toString();
    }
}

// Runner class to test the QueueADT
class QueueADTRunner {
    public static void main(String[] args) {
        // Main method for testing
    }
}