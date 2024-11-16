// FILE: adt/AbstractList.java
package BooksApp.adt;

// Interface for a generic list
public interface AbstractList<E> {
    // Adds an element to the end of the list
    boolean add(E element);

    // Adds an element at a specific index in the list
    boolean add(int index, E element);

    // Retrieves the element at a specific index
    E get(int index);

    // Replaces the element at a specific index with a new element
    E set(int index, E element);

    // Removes the element at a specific index
    E remove(int index);

    // Removes the first occurrence of a specific element
    boolean remove(E element); // New method to remove an element by its value

    // Returns the number of elements in the list
    int size();

    // Returns the index of the first occurrence of a specific element
    int indexOf(E element);

    // Checks if the list contains a specific element
    boolean contains(E element);

    // Checks if the list is empty
    boolean isEmpty();
}