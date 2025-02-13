package org.example.sortingvisualizer.Utility;

import javafx.concurrent.Task;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.example.sortingvisualizer.Interface.ISortUpdateListener;
import org.example.sortingvisualizer.Interface.ISortingAlgorithm;

public class Sorter {
    private ISortingAlgorithm algorithm;
    private ISortUpdateListener listener;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private volatile boolean running;
    private double sortingSpeed = 50; // Default sorting speed

    public Sorter(ISortingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setSortingAlgorithm(ISortingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setListener(ISortUpdateListener listener) {
        this.listener = listener;
    }

    public void setSortingSpeed(double sortingSpeed) {
        this.sortingSpeed = sortingSpeed;
    }

    public Task<Void> sort(int[] array) {
        running = true;
        Task<Void> sortTask = algorithm.sort(array);
        sortTask.setOnFailed(e -> running = false); // Set running to false if the task fails
        executorService.submit(sortTask);
        return sortTask;
    }

    public void stopSort() {
        running = false;
        executorService.shutdownNow(); // Attempt to stop all actively executing tasks
    }

    public boolean isRunning() {
        return running;
    }

    public double getSortingSpeed() {
        return sortingSpeed;
    }
}
