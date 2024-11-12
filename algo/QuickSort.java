package BooksApp.algo;

import BooksApp.adt.ArrayListADT;

public class QuickSort<T extends Comparable<T>> {
    public void sort(ArrayListADT<T> list) {
        sort(list, 0, list.size() - 1);
    }

    private void sort(ArrayListADT<T> list, int start, int end) {
        // base condition
        if (start >= end)
            return;

        // Partition
        int boundary = partition(list, start, end);

        // Sort left
        sort(list, start, boundary - 1);

        // Sort right
        sort(list, boundary + 1, end);
    }

    private int partition(ArrayListADT<T> list, int start, int end) {
        T pivot = list.get(end);
        int boundary = start - 1;
        for (int i = start; i <= end; i++) {
            if (list.get(i).compareTo(pivot) <= 0) {
                boundary++;

                // swap two elements
                T temp = list.get(i);
                list.set(i, list.get(boundary));
                list.set(boundary, temp);
            }
        }
        return boundary;
    }
}