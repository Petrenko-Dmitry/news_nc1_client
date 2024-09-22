package com.example.news_nc1_client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewsApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            var loader = new FXMLLoader(getClass().getResource("news_layout.fxml"));
            VBox root = loader.load();

            var scene = new Scene(root, 600, 400);
            primaryStage.setTitle("News Application");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}