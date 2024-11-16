// FILE: adt/ArrayListADT.java
package BooksApplication.adt;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayListADT<E> implements AbstractList<E> {

    // Default capacity of the array
    private final int DEFAULT_CAPACITY = 10;
    // Array to store elements
    private E[] elements;
    // Number of elements in the list
    private int size;

    // Constructor to initialize the array and size
    public ArrayListADT() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Adds an element to the end of the list
    @Override
    public boolean add(E element) {
        // Check if the array needs to be resized
        if (this.size == this.elements.length) {
            this.elements = grow();
        }

        // Add the new element
        this.elements[size] = element;
        this.size++;
        return true;
    }

    // Resizes the array to double its current size
    private E[] grow() {
        return Arrays.copyOf(this.elements, this.elements.length * 2);
    }

    // Adds an element at a specific index in the list
    @Override
    public boolean add(int index, E element) {
        // Check if the index is valid
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }

        // Check if the array needs to be resized
        if (this.size() == this.elements.length) {
            this.elements = grow();
        }

        // Shift the remaining elements to the right
        for (int i = size; i >= index + 1; i--) {
            elements[i] = elements[i - 1];
        }
        // Add the new element
        this.elements[index] = element;
        this.size++;
        return true;
    }

    // Retrieves the element at a specific index
    @Override
    public E get(int index) {
        // Check if the index is valid
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }

        return this.elements[index];
    }

    // Replaces the element at a specific index with a new element
    @Override
    public E set(int index, E element) {
        E oldElement = this.get(index);
        this.elements[index] = element;
        return oldElement;
    }

    // Removes the element at a specific index
    @Override
    public E remove(int index) {
        E removedElement = this.get(index);

        // Shift the remaining elements to the left
        for (int i = index; i < this.size() - 1; i++) {
            elements[i] = elements[i + 1];
        }

        this.size--;
        this.elements[size] = null;
        return removedElement;
    }

    // Removes the first occurrence of a specific element
    public boolean remove(E element) {
        int index = this.indexOf(element);
        if (index != -1) {
            this.remove(index);
            return true;
        }
        return false;
    }

    // Returns the number of elements in the list
    @Override
    public int size() {
        return this.size;
    }

    // Returns the index of the first occurrence of a specific element
    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size(); i++) {
            if (this.elements[i] == element) {
                return i;
            }
        }
        return -1;
    }

    // Checks if the list contains a specific element
    @Override
    public boolean contains(E element) {
        for (int i = 0; i < this.size(); i++) {
            if (this.elements[i] == element) {
                return true;
            }
        }
        return false;
    }

    // Checks if the list is empty
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Returns a string representation of the list
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");

        for (int i = 0; i < this.size; i++) {
            result.append(this.get(i));
            if (i < this.size - 1) {
                result.append(", ");
            }
        }

        result.append("]");
        return result.toString();
    }

    // Clears the list
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;
    }
}