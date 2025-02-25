package com.oris.edu.patterns.observer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Face(), 450, 450);
        stage.setTitle("Smile");
        stage.setScene(scene);
        stage.show();
    }
}
