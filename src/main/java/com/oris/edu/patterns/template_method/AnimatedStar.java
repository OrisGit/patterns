package com.oris.edu.patterns.template_method;

import com.oris.edu.patterns.template_method.animation.ShapeAnimation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class AnimatedStar extends AnimatedShape<Canvas> {
    public AnimatedStar(double width, Color color, Node canvas) {
        super(new Canvas(width, width), canvas);
        GraphicsContext gc = super.shape.getGraphicsContext2D();
        drawStarShape(gc, color);
    }

    private void drawStarShape(GraphicsContext gc, Color color) {
        double[] xpoints = {9,6,0,5,5,9,14,14,19,12};
        double[] ypoints = {0,7,9,13,19,15,19,13,9,7};

        gc.setFill(color);
        gc.fillPolygon(xpoints, ypoints, xpoints.length);
    }

    private Random random = new Random();

    @Override
    protected EventHandler<ActionEvent> animation() {
        return new StarAnimation(shape, canvas);
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

    private static class StarAnimation extends ShapeAnimation<Canvas> {

        public StarAnimation(Canvas shape, Node canvas) {
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
