package com.oris.edu.patterns.template_method;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class BouncingBall extends Application {
    private static Random random = new Random();

    @Override
    public void start(Stage stage) {
        Pane canvas = new Pane();
        Button button = new Button("Пуск");
        button.setOnMouseClicked(event -> {
            switch (random.nextInt(3)){
                case 0:
                    AnimatedBall ball = new AnimatedBall(10, getRandomColor(), canvas);
                    ball.randomRelocate();
                    canvas.getChildren().add(ball.getShape());
                    ball.startAnimation();
                    break;
                case 1:
                    AnimatedRectangle rectangle = new AnimatedRectangle(15, 15, getRandomColor(), canvas);
                    rectangle.randomRelocate();
                    canvas.getChildren().add(rectangle.getShape());
                    rectangle.startAnimation();
                    break;
                case 2:
                    AnimatedStar star = new AnimatedStar(20, getRandomColor(), canvas);
                    star.randomRelocate();
                    canvas.getChildren().add(star.getShape());
                    star.startAnimation();
                    break;
            }

        });
        canvas.getChildren().add(button);
        Scene scene = new Scene(canvas, 1000, 1000, Color.ALICEBLUE);

        stage.setTitle("Animated Ball");
        stage.setScene(scene);

        stage.show();
    }

    public static Color getRandomColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return Color.rgb(r, g, b);
    }

    public static void main(String[] args) {
        launch();
    }
}
