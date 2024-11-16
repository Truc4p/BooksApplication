// FILE: adt/StackADT.java
package BooksApplication.adt;

import java.util.NoSuchElementException;

// Implementation of a generic stack
public class StackADT<E> implements AbstractStack<E> {
    // Inner class to represent a node in the stack
    private class Node<E> {
        private E element; // The element stored in the node
        private Node<E> next; // The next node in the stack

        // Constructor to initialize the node with an element
        private Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    private int size; // The number of elements in the stack
    private Node<E> top; // The top of the stack

    // Constructor to initialize the stack
    public StackADT() {
        this.top = null;
        this.size = 0;
    }

    // Pushes an element onto the top of the stack
    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = top;
        this.top = newNode;
        this.size++;
    }

    // Removes and returns the top element from the stack
    @Override
    public E pop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }

        E oldElement = this.top.element;

        // Option 1
        /*Node<E> tempNode = top;
        this.top = this.top.next;
        tempNode.next = null;
        this.size--;*/

        // Option 2
        Node<E> tempNode = top.next;
        this.top.next = null;
        this.top = tempNode;
        this.size--;

        return oldElement;
    }

    // Returns the top element of the stack without removing it
    @Override
    public E peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }

        return this.top.element;
    }

    // Returns the number of elements in the stack
    @Override
    public int size() {
        return this.size;
    }

    // Checks if the stack is empty
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Returns a string representation of the stack
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> tempNode = top;

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

// Runner class to test the StackADT
class StackADTRunner {
    public static void main(String[] args) {
        // Main method for testing
    }
}