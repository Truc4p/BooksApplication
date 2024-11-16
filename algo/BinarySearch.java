// FILE: algo/BinarySearch.java
package BooksApplication.algo;

import BooksApplication.adt.ArrayListADT;

public class BinarySearch<T extends Comparable<T>> {
    // Public method to initiate binary search on the list
    public int search(ArrayListADT<T> list, T key) {
        return search(list, key, 0, list.size() - 1);
    }

    // Private recursive method to perform binary search
    private int search(ArrayListADT<T> list, T key, int start, int end) {
        if (start > end) {
            return -1; // Key not found
        }

        int mid = start + (end - start) / 2; // Calculate mid index
        T midVal = list.get(mid); // Get value at mid index

        if (midVal.compareTo(key) == 0) {
            return mid; // Key found
        } else if (midVal.compareTo(key) > 0) {
            return search(list, key, start, mid - 1); // Search left half
        } else {
            return search(list, key, mid + 1, end); // Search right half
        }
    }
}