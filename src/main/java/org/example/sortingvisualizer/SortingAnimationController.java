package org.example.sortingvisualizer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.concurrent.Task;
import org.example.sortingvisualizer.Interface.ISortUpdateListener;
import org.example.sortingvisualizer.Interface.ISortingAlgorithm;
import org.example.sortingvisualizer.Utility.Sorter;

public class SortingAnimationController implements ISortUpdateListener {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private Slider speedSlider;

    @FXML
    private ChoiceBox<String> algorithmChoice;

    @FXML
    private TextField arraySizeInput;

    private int[] data;
    private Sorter sorter;
    private Task<Void> currentSortingTask;

    @FXML
    public void initialize() {
        data = createShuffledArray(50);
        sorter = new Sorter(null); // Initialize sorter without a default algorithm
        sorter.setListener(this);
        drawChart();
        populateAlgorithmChoice();

        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sorter.setSortingSpeed(newValue.doubleValue());
        });
    }

    private void populateAlgorithmChoice() {
        algorithmChoice.getItems().addAll(SortingAlgorithmFactory.getAvailableAlgorithms());
    }

    @FXML
    public void startSorting() {
        if (currentSortingTask != null && currentSortingTask.isRunning()) {
            return; // A sorting task is already running
        }
        String selectedAlgorithm = algorithmChoice.getValue();
        if (selectedAlgorithm == null) {
            System.err.println("Please select an algorithm.");
            return;
        }

        ISortingAlgorithm algorithm = SortingAlgorithmFactory.createAlgorithm(selectedAlgorithm, this, sorter);
        sorter.setSortingAlgorithm(algorithm);
        currentSortingTask = sorter.sort(data);
    }

    private int[] createShuffledArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (i + 1);
        }
        // Shuffle the array
        for (int i = array.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
        return array;
    }

    @FXML
    public void stopSorting() {
        if (currentSortingTask != null) {
            currentSortingTask.cancel();
        }
    }

    @FXML
    public void resetArray() {
        if (currentSortingTask != null && currentSortingTask.isRunning()) {
            return; // It's not safe to reset the array while sorting
        }
        updateArraySize();
    }

    @FXML
    public void updateArraySize() {
        if (currentSortingTask != null && currentSortingTask.isRunning()) {
            return; // It's not safe to update array size while sorting
        }
        int newSize = Integer.parseInt(arraySizeInput.getText());
        data = createShuffledArray(newSize);
        drawChart();

        // Force a layout update to refresh the chart
        barChart.requestLayout();

        arraySizeInput.getParent().requestFocus();
    }

    private void drawChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 0; i < data.length; i++) {
            series.getData().add(new XYChart.Data<>(String.valueOf(i), data[i]));
        }
        barChart.getData().clear();
        barChart.getData().add(series);

        // Update the X-axis labels
        for (int i = 0; i < barChart.getData().size(); i++) {
            for (int j = 0; j < barChart.getData().get(i).getData().size(); j++) {
                XYChart.Data<String, Number> dataPoint = barChart.getData().get(i).getData().get(j);
                dataPoint.setXValue(String.valueOf(j));
            }
        }
    }

    private void updateBarChart(int index1, int index2) {
        XYChart.Series<String, Number> series = barChart.getData().getFirst();
        XYChart.Data<String, Number> data1 = series.getData().get(index1);
        XYChart.Data<String, Number> data2 = series.getData().get(index2);

        // Swap the YValues of data1 and data2 (use "tmp" to store previous value of data1)
        Number tmp = data1.getYValue();
        data1.setYValue(data2.getYValue());
        data2.setYValue(tmp);
    }

    @Override
    public void onSwap(int index1, int index2) {
        Platform.runLater(() -> {
            updateBarChart(index1, index2);
        });
    }

    @Override
    public void onCompare(int index1, int index2) {
        // Optionally update the UI to reflect comparisons
    }

    @Override
    public void onUpdate(int[] array) {
        Platform.runLater(() -> {
            XYChart.Series<String, Number> series = barChart.getData().getFirst(); // Assuming only one series is used

            // Update the series with the new values without clearing
            for (int i = 0; i < array.length; i++) {
                XYChart.Data<String, Number> data = series.getData().get(i);
                data.setYValue(array[i]);
            }

            // Optional: Add styling/animations
        });
    }

}
