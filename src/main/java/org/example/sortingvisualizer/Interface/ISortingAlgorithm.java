package org.example.sortingvisualizer.Interface;

import javafx.concurrent.Task;

/**
 * Interface for sorting algorithms.
 * Implementations should provide a sorting method that sorts the given array in place.
 *
 * Preconditions:
 * The array should not be null.
 * The array should contain comparable elements.
 *
 * Postconditions:
 * The array should be sorted in ascending order.
 * No elements should be added or removed from the array.
 */
public interface ISortingAlgorithm {
    /**
     * Sorts the given array in place.
     *
     * @param array the array to be sorted
     * @return a Task representing the sorting operation
     */
    Task<Void> sort(int[] array);
}
