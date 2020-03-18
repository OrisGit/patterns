package com.oris.edu.patterns.template_method;

import com.oris.edu.patterns.template_method.animation.ShapeAnimation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class AnimatedRectangle extends AnimatedShape<Rectangle> {

    private Random random = new Random();


    public AnimatedRectangle(double width, double height, Color color, Node canvas) {
        super(new Rectangle(width, height, color), canvas);
    }

    @Override
    protected EventHandler<ActionEvent> animation() {
        return new RectangleAnimation(shape, canvas);
    }

    @Override
    public void randomRelocate() {
        Bounds layoutBounds = canvas.getLayoutBounds();
        double maxX = layoutBounds.getMaxX() - shape.getHeight();
        double maxY = layoutBounds.getMaxY() - shape.getWidth();
        double v = random.nextInt((int) maxX);
        double v1 = random.nextInt((int) maxY);
        System.out.println("v: " + v + " v1: " + v1);
        shape.relocate(v, v1);
    }

    private static class RectangleAnimation extends ShapeAnimation<Rectangle> {

        public RectangleAnimation(Rectangle shape, Node canvas) {
            super(shape, canvas);
        }

        @Override
        protected void calculateDirectionFromHorizontalBorder() {
            yStep = -yStep;
        }

        @Override
        protected boolean isHorizontalBorder() {
            return shape.getLayoutY() >= (bounds.getMaxY() - shape.getHeight()) ||
                    shape.getLayoutY() <= (bounds.getMinY() + shape.getHeight());
        }

        @Override
        protected void calculateDirectionFromVerticalBorder() {
            xStep = -xStep;
        }

        @Override
        protected boolean isVerticalBorder() {
            return shape.getLayoutX() <= (bounds.getMinX() + shape.getWidth()) ||
                    shape.getLayoutX() >= (bounds.getMaxX() - shape.getWidth());
        }
    }
}
