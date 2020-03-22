package com.oris.edu.patterns.mvc;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Point2D> table = new ArrayList<>();
    private List<View> views = new ArrayList<>();

    public void addPoint(double x){
        table.add(new Point2D(x,calculateY(x)));
        views.forEach(view -> view.updateView(table));
    }

    public void updatePoint(int index, double newValue){
        table.set(index, new Point2D(newValue, calculateY(newValue)));
        views.forEach(view -> view.updateView(table));
    }

    public void removePoint(int index){
        table.remove(index);
        views.forEach(view -> view.updateView(table));
    }

    public double calculateY(double x) {
        return Math.sin(x);
    }

    public void subscribe(View view){
        views.add(view);
    }
}
