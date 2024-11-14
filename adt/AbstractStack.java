package BooksApp.adt;

public interface AbstractStack<E> {
    // Pushes an element onto the top of the stack
    void push(E element);

    // Removes and returns the top element from the stack
    E pop();

    // Returns the top element of the stack without removing it
    E peek();

    // Returns the number of elements in the stack
    int size();

    // Checks if the stack is empty
    boolean isEmpty();
}