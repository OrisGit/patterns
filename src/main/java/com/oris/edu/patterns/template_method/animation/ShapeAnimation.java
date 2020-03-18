package com.oris.edu.patterns.template_method.animation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;

public abstract class ShapeAnimation<T extends Node> implements EventHandler<ActionEvent> {
    protected final Node canvas;
    protected final T shape;
    protected final Bounds bounds;
    protected double xStep = 7; // Шаг по х или скорости
    protected double yStep = 3; // наступи на тебя

    public ShapeAnimation(T shape, Node canvas) {
        this.canvas = canvas;
        this.shape = shape;
        this.bounds = canvas.getLayoutBounds();
    }

    @Override
    public void handle(ActionEvent event) {
        shape.setLayoutX(shape.getLayoutX() + xStep);
        shape.setLayoutY(shape.getLayoutY() + yStep);

        if (isVerticalBorder()) {
            calculateDirectionFromVerticalBorder();
        }

        if (isHorizontalBorder()) {
            calculateDirectionFromHorizontalBorder();
        }
    }

    protected abstract void calculateDirectionFromHorizontalBorder();

    protected abstract boolean isHorizontalBorder();

    protected abstract void calculateDirectionFromVerticalBorder();

    protected abstract boolean isVerticalBorder();

}
