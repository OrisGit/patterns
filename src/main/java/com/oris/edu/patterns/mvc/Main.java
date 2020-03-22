package com.oris.edu.patterns.mvc;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        HBox root = new HBox();

        primaryStage.setTitle("MVC");

        Model model = new Model();
        Controller controller = new Controller(model);

        LineChart<Number, Number> lineChart = createLineChart();
        TableView tableView = createTable(controller);

        VBox tableRoot = new VBox();
        tableRoot.setFillWidth(true);
        Button removeButton = new Button("Удалить");
        removeButton.setPrefWidth(200);
        TextField textField = new TextField();
        textField.setPrefWidth(200);

        HBox btnRoot = new HBox();
        btnRoot.setPrefWidth(200);
        Button addButton = new Button("Добавить");
        addButton.setPrefWidth(100);
        Button updateButton = new Button("Обновить");
        updateButton.setPrefWidth(100);
        btnRoot.getChildren().addAll(addButton, updateButton);
        tableRoot.getChildren().addAll(tableView, removeButton, textField, btnRoot);

        root.getChildren().addAll(lineChart, tableRoot);
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        CustomView customView = new CustomView(tableView, lineChart);
        model.subscribe(customView);

        addButton.setOnMouseClicked(event -> {
            String text = textField.getText();
            controller.addPoint(Double.parseDouble(text));
            tableView.getSelectionModel().clearSelection();
            textField.clear();
        });

        updateButton.setOnMouseClicked(event -> {
            String text = textField.getText();
            if (tableView.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Ни одна строка в таблице не выбрана");
                alert.show();
            }else{
                int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
                controller.updatePoint(selectedIndex, Double.parseDouble(text));
                tableView.getSelectionModel().clearSelection();
                textField.clear();
            }
        });

        removeButton.setOnMouseClicked(event -> {
            if (tableView.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Ни одна строка в таблице не выбрана");
                alert.show();
            } else {
                int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
                controller.removePoint(selectedIndex);
                textField.clear();
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Point2D point = (Point2D) tableView.getSelectionModel().getSelectedItem();
                textField.setText(String.valueOf(point.getX()));
            }
        });

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    char[] chars = newValue.toCharArray();
                    for (char aChar : chars) {
                        if(Character.isAlphabetic(aChar)){
                            textField.setText(oldValue);
                            return;
                        }
                    }
                    try{
                        Double.parseDouble(newValue);
                        textField.setText(newValue);
                    }catch (NumberFormatException e){
                        textField.setText(oldValue);
                    }
                }
            }
        });


        primaryStage.show();
    }

    private TableView createTable(Controller controller){
        TableView<Point2D> tableView = new TableView<>();
        tableView.setPrefHeight(580);
        TableColumn<Point2D, Double> xColumn = new TableColumn<>("X");
        TableColumn<Point2D, Double> yColumn = new TableColumn<>("Y");
        xColumn.setCellValueFactory(new PropertyValueFactory<>("X"));
        yColumn.setCellValueFactory(new PropertyValueFactory<>("Y"));
        yColumn.setEditable(false);
        xColumn.setEditable(true);
        xColumn.setResizable(false);
        yColumn.setResizable(false);
        xColumn.setPrefWidth(100);
        tableView.setEditable(true);
        yColumn.setPrefWidth(100);
        tableView.getColumns().addAll(xColumn, yColumn);
        xColumn.setOnEditCommit(event -> {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            controller.updatePoint(selectedIndex, event.getNewValue());
        });

        return tableView;
    }

    private LineChart<Number, Number> createLineChart(){
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();
        LineChart<Number, Number> numberLineChart = new LineChart<>(x, y);
        numberLineChart.setPrefWidth(800);
        numberLineChart.setTitle("График");
        return numberLineChart;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
