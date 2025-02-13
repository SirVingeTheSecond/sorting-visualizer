package org.example.sortingvisualizer.SortingAlgorithms;

import org.example.sortingvisualizer.Interface.ISortUpdateListener;
import org.example.sortingvisualizer.SortingAlgorithmTemplate;
import org.example.sortingvisualizer.Utility.Sorter;

public class RadixSort extends SortingAlgorithmTemplate {

    public RadixSort(ISortUpdateListener listener, Sorter sorter) {
        super(listener, sorter);
    }

    @Override
    protected void doSort(int[] array) {
        if (array.length == 0) {
            return;
        }

        // Find the maximum number to know the number of digits
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        // Perform counting sort on each digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp);
            listener.onUpdate(array); // Update visualization after each iteration
            sleep(Math.max(1, (long) (1000 / sorter.getSortingSpeed()))); // Make sure the animation duration is at least 1 ms.
        }
    }

    private void countingSortByDigit(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Initialize count array
        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        // Change count[i] so that count[i] now contains the position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        // Copy the output array to array, so that array now contains sorted numbers according to current digit
        System.arraycopy(output, 0, array, 0, n);
    }
}
