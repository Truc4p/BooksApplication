// FILE: adt/AbstractQueue.java
package BooksApp.adt;

public interface AbstractQueue<E> {
    // Inserts the specified element into the queue
    void offer(E element);

    // Retrieves and removes the head of this queue, or returns null if this queue is empty
    E poll();

    // Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty
    E peek();

    // Returns the number of elements in this queue
    int size();

    // Returns true if this queue contains no elements
    boolean isEmpty();
}