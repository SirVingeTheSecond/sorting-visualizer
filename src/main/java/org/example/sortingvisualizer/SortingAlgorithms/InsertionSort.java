package org.example.sortingvisualizer.SortingAlgorithms;

import org.example.sortingvisualizer.Interface.ISortUpdateListener;
import org.example.sortingvisualizer.SortingAlgorithmTemplate;
import org.example.sortingvisualizer.Utility.Sorter;

public class InsertionSort extends SortingAlgorithmTemplate {

    public InsertionSort(ISortUpdateListener listener, Sorter sorter) {
        super(listener, sorter);
    }

    @Override
    protected void doSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            // Move elements of array[0..i-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && array[j] > key) {
                listener.onCompare(j, j + 1); // Call onCompare before swapping elements

                array[j + 1] = array[j];
                j = j - 1;

                listener.onSwap(j + 1, j + 2); // Call onSwap after the element has been moved
                sleep(Math.max(1, (long) (1000 / sorter.getSortingSpeed()))); // Make sure the animation duration is at least 1 ms.
            }
            array[j + 1] = key;
            // Call onUpdate outside of the inner loop to update the bar chart less frequently
        }
        listener.onUpdate(array); // Update the bar chart after the entire array is sorted
    }
}

