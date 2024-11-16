// FILE: algo/QuickSort.java
package BooksApp.algo;

import BooksApp.adt.ArrayListADT;

public class QuickSort<T extends Comparable<T>> {
    // Public method to initiate the quicksort on the list
    public void sort(ArrayListADT<T> list) {
        sort(list, 0, list.size() - 1);
    }

    // Private recursive method to perform quicksort
    private void sort(ArrayListADT<T> list, int start, int end) {
        // Base condition to end recursion
        if (start >= end)
            return;

        // Partition the list and get the boundary index
        int boundary = partition(list, start, end);

        // Sort the left part of the list
        sort(list, start, boundary - 1);

        // Sort the right part of the list
        sort(list, boundary + 1, end);
    }

    // Private method to partition the list
    private int partition(ArrayListADT<T> list, int start, int end) {
        T pivot = list.get(end); // Choose the pivot element
        int boundary = start - 1; // Initialize the boundary index
        for (int i = start; i <= end; i++) {
            // Compare current element with the pivot
            if (list.get(i).compareTo(pivot) <= 0) {
                boundary++;

                // Swap two elements
                T temp = list.get(i);
                list.set(i, list.get(boundary));
                list.set(boundary, temp);
            }
        }
        return boundary; // Return the boundary index
    }
}