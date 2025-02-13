package org.example.sortingvisualizer.Interface;

import org.example.sortingvisualizer.Utility.Sorter;

public interface ISortingAlgorithmSupplier {
    ISortingAlgorithm get(ISortUpdateListener listener, Sorter sorter);
}

