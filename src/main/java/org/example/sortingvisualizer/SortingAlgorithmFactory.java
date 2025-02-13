package org.example.sortingvisualizer;

import org.example.sortingvisualizer.Interface.ISortUpdateListener;
import org.example.sortingvisualizer.Interface.ISortingAlgorithm;
import org.example.sortingvisualizer.Interface.ISortingAlgorithmSupplier;
import org.example.sortingvisualizer.SortingAlgorithms.*;
import org.example.sortingvisualizer.Utility.Sorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortingAlgorithmFactory {
    private static final Map<String, ISortingAlgorithmSupplier> algorithms = new HashMap<>();

    // Executed when the class is loaded into memory
    static {
        algorithms.put("Bubble Sort", BubbleSort::new);
        algorithms.put("Insertion Sort", InsertionSort::new);
        algorithms.put("Merge Sort", MergeSort::new);
        algorithms.put("Qucik Sort", QuickSort::new);
        algorithms.put("Radix Sort", RadixSort::new);
        // New algorithms goes here
    }

    public static ISortingAlgorithm createAlgorithm(String algorithmName, ISortUpdateListener listener, Sorter sorter) {
        ISortingAlgorithmSupplier algorithmSupplier = algorithms.get(algorithmName);
        if (algorithmSupplier == null) {
            throw new IllegalArgumentException("Unknown algorithm: " + algorithmName);
        }
        return algorithmSupplier.get(listener, sorter);
    }

    public static List<String> getAvailableAlgorithms() {
        return new ArrayList<>(algorithms.keySet());
    }
}

