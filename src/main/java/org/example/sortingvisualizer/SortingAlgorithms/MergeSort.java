package org.example.sortingvisualizer.SortingAlgorithms;

import org.example.sortingvisualizer.Interface.ISortUpdateListener;
import org.example.sortingvisualizer.SortingAlgorithmTemplate;
import org.example.sortingvisualizer.Utility.Sorter;

public class MergeSort extends SortingAlgorithmTemplate {

    public MergeSort(ISortUpdateListener listener, Sorter sorter) {
        super(listener, sorter);
    }

    @Override
    protected void doSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            // Find the middle point to divide the array into two halves
            int middle = left + (right - left) / 2;

            // Recursively sort the two halves
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            // Merge the sorted halves
            merge(array, left, middle, right);

            // Visualize the sorting step
            listener.onUpdate(array);
            sleep(Math.max(1, (long) (1000 / sorter.getSortingSpeed()))); // Make sure the animation duration is at least 1 ms.
        }
    }

    private void merge(int[] array, int left, int middle, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temporary arrays L[] and R[]
        for (int i = 0; i < n1; ++i) {
            L[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = array[middle + 1 + j];
        }

        // Merge the temporary arrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }
}
