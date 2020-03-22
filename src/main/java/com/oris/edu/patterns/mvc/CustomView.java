package com.oris.edu.patterns.mvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;

import java.util.List;

public class CustomView implements View {

    private final TableView tableView;
    private final LineChart<Number, Number> numberLineChart;

    public CustomView(TableView tableView, LineChart<Number, Number> numberLineChart) {
        this.tableView = tableView;
        this.numberLineChart = numberLineChart;
    }

    @Override
    public void updateView(List<Point2D> points) {
        tableView.getItems().clear();
        tableView.getItems().addAll(points);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("sin(x)");
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        points.forEach(point -> datas.add(new XYChart.Data(point.getX(), point.getY())));
        series1.setData(datas);
        numberLineChart.getData().clear();
        numberLineChart.getData().add(series1);
    }
}
