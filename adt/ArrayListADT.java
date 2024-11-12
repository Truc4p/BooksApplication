package BooksApp.adt;

import java.util.Arrays;

public class ArrayListADT<E> implements AbstractList<E> {

    // data fields
    private final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;

    // constructor
    public ArrayListADT() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // methods (operations)
    @Override
    public boolean add(E element) {
        // check size of array
        if (this.size == this.elements.length) {
            this.elements = grow();
        }

        // add new element
        this.elements[size] = element;
        this.size++;
        return true;
    }

    private E[] grow() {
        return Arrays.copyOf(this.elements, this.elements.length * 2);
    }

    @Override
    public boolean add(int index, E element) {

        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }

        if (this.size() == this.elements.length) {
            this.elements = grow();
        }

        // shift the remaining elements
        for (int i = size; i >= index + 1; i--) {
            elements[i] = elements[i - 1];
        }
        this.elements[index] = element;
        this.size++;
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }

        return this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        E oldElement = this.get(index);
        this.elements[index] = element;
        return oldElement;
    }

    @Override
    public E remove(int index) {
        E removedElement = this.get(index);

        for (int i = index; i < this.size() - 1; i++) {
            elements[i] = elements[i + 1];
        }

        this.size--;
        this.elements[size] = null;
        return removedElement;
    }

    // New method to remove an element by its value
    public boolean remove(E element) {
        int index = this.indexOf(element);
        if (index != -1) {
            this.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {

        for (int i = 0; i < this.size(); i++) {
            if (this.elements[i] == element) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < this.size(); i++) {
            if (this.elements[i] == element) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        if (this.size() == 0) {
            return true;
        }
        return false;
    }

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

    // Method to clear the list
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;
    }
}
