package com.oris.edu.patterns.template_method;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public abstract class AnimatedShape<T extends Node> {
    private Timeline timeline;
    protected Node canvas;
    protected T shape;

    public AnimatedShape(T shape, Node canvas) {
        this.canvas = canvas;
        this.shape = shape;
        timeline = new Timeline(new KeyFrame(Duration.millis(20), animation()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    protected abstract EventHandler<ActionEvent> animation();

    public abstract void randomRelocate();

    public T getShape(){
        return shape;
    }

    public void startAnimation() {
        timeline.play();
    }


}
