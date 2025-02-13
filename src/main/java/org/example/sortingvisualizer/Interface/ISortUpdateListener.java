package org.example.sortingvisualizer.Interface;

public interface ISortUpdateListener {
    void onSwap(int index1, int index2);
    void onCompare(int index1, int index2);
    void onUpdate (int[] array);
}
