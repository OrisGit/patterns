package com.oris.edu.patterns.mvc;

public class Controller {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void addPoint(double x){
        model.addPoint(x);
    }

    public void updatePoint(int index, double newValue){
        model.updatePoint(index, newValue);
    }

    public void removePoint(int index){
        model.removePoint(index);
    }

}
