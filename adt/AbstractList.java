package BooksApp.adt;

public interface AbstractList<E> {
    boolean add (E element);
    boolean add (int index, E element);
    E get(int index);
    E set(int index, E element);
    E remove(int index);
    boolean remove(E element); // New method to remove an element by its value
    int size();
    int indexOf(E element);
    boolean contains(E element);
    boolean isEmpty();
}
