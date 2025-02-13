package org.example.sortingvisualizer.SortingAlgorithms;

import org.example.sortingvisualizer.Interface.ISortUpdateListener;
import org.example.sortingvisualizer.SortingAlgorithmTemplate;
import org.example.sortingvisualizer.Utility.Sorter;

public class BubbleSort extends SortingAlgorithmTemplate {
    public BubbleSort(ISortUpdateListener listener, Sorter sorter) {
        super(listener, sorter);
    }

    @Override
    protected void doSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    listener.onSwap(j, j + 1);
                    sleep(Math.max(1, (long) (1000 / sorter.getSortingSpeed()))); // Make sure the animation duration is at least 1 ms.
                }
                listener.onCompare(j, j + 1);
            }
        }
    }

}
