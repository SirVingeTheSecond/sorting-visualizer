module org.example.sortingvisualizer {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.sortingvisualizer to javafx.fxml;
    exports org.example.sortingvisualizer;
    exports org.example.sortingvisualizer.Utility;
    opens org.example.sortingvisualizer.Utility to javafx.fxml;
    exports org.example.sortingvisualizer.Interface;
    opens org.example.sortingvisualizer.Interface to javafx.fxml;
    exports org.example.sortingvisualizer.SortingAlgorithms;
    opens org.example.sortingvisualizer.SortingAlgorithms to javafx.fxml;
}