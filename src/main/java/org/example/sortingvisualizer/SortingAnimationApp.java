package org.example.sortingvisualizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SortingAnimationApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/sortingvisualizer/SortingAnimation.fxml"));
        primaryStage.setTitle("Sorting Animation");
        Scene scene = new Scene(root, 800, 600);

        // Add the CSS stylesheet to the scene
        scene.getStylesheets().add(getClass().getResource("/org/example/sortingvisualizer/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
