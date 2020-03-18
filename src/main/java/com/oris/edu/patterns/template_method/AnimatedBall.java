package com.oris.edu.patterns.template_method;

import com.oris.edu.patterns.template_method.animation.ShapeAnimation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class AnimatedBall extends AnimatedShape<Circle> {

    private Random random = new Random();


    public AnimatedBall(double radius, Color color, Node canvas) {
        super(new Circle(radius, color), canvas);
    }

    @Override
    protected EventHandler<ActionEvent> animation() {
        return new BallAnimation(7, 3, shape, canvas);
    }

    @Override
    public void randomRelocate() {
        Bounds layoutBounds = canvas.getLayoutBounds();
        double maxX = layoutBounds.getMaxX() - shape.getRadius();
        double maxY = layoutBounds.getMaxY() - shape.getRadius();
        double v = random.nextInt((int) maxX);
        double v1 = random.nextInt((int)maxY);
        System.out.println("v: "+v+" v1: "+v1);
        shape.relocate(v,v1);
    }

    private static class BallAnimation extends ShapeAnimation<Circle> {

        public BallAnimation(double initialXStep, double initialYStep, Circle shape, Node canvas) {
            super(shape, canvas);
        }

        @Override
        protected void calculateDirectionFromHorizontalBorder() {
            yStep = -yStep;
        }

        @Override
        protected boolean isHorizontalBorder() {
            return shape.getLayoutY() >= (bounds.getMaxY() - shape.getRadius()) ||
                    shape.getLayoutY() <= (bounds.getMinY() + shape.getRadius());
        }

        @Override
        protected void calculateDirectionFromVerticalBorder() {
            xStep = -xStep;
        }

        @Override
        protected boolean isVerticalBorder() {
            return shape.getLayoutX() <= (bounds.getMinX() + shape.getRadius()) ||
                    shape.getLayoutX() >= (bounds.getMaxX() - shape.getRadius());
        }
    }
}
