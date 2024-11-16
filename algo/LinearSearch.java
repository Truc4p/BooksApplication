// FILE: algo/LinearSearch.java
package BooksApplication.algo;

import BooksApplication.adt.ArrayListADT;

public class LinearSearch<T> {

    // Searches for an element that exactly matches the key using the equals method.
    // Example: Finding a book with a specific ID.
    public int search(ArrayListADT<T> list, T key) {
        for (int i = 0; i < list.size(); i++) {
            // Check if the current element equals the key
            if (list.get(i).equals(key)) {
                return i; // Key found
            }
        }
        return -1; // Key not found
    }

    // Searches for an element that matches a custom condition defined by the predicate.
    // Example: Finding a book with a title that contains a specific word.
    public int search(ArrayListADT<T> list, T key, java.util.function.Predicate<T> predicate) {
        for (int i = 0; i < list.size(); i++) {
            // Check if the current element matches the predicate condition
            if (predicate.test(list.get(i))) {
                return i; // Key found
            }
        }
        return -1; // Key not found
    }
}