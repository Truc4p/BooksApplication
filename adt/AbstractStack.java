package BooksApp.adt;

public interface AbstractStack<E> {
    /**
     * Pushes an element onto the top of the stack.
     * @param element the element to push
     */
    void push( E element );

    /**
     * Removes and returns the top element from the stack.
     * @return the top element, or null if the stack is empty
     */
    E pop( );

    /**
     * Returns the top element of the stack without removing it.
     * @return the top element, or null if the stack is empty
     */
    E peek( );

    /**
     * Returns the number of elements in the stack.
     * @return the size of the stack
     */
    int size( );

    /**
     * Checks if the stack is empty.
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty( );
}
