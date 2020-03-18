package com.oris.edu.patterns.observer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Face extends Group {
    private final double faceCenterX = 220.0;
    private final double faceCenterY = 220.0;
    private final double eyeYFromFaceCenter = 40.0;
    private final double eyeXFromFaceCenter = 80.0;

    public Face() {
        super();
        Circle face = createFace();
        Line closedEyeLeft = createClosedEye(Side.LEFT);
        Line closedEyeRight = createClosedEye(Side.RIGHT);
        Group openEyeLeft = createOpenEye(Side.LEFT);
        Group openEyeRight = createOpenEye(Side.RIGHT);
        Circle mouth = createMouth();
        Rectangle blackNose = createNose(Color.BLACK);
        Rectangle redNose = createNose(Color.RED);
        Arc smile = createSmile();

        setOnMouseClicked(openEyeLeft, closedEyeLeft);
        setOnMouseClicked(closedEyeLeft, openEyeLeft);
        setOnMouseClicked(openEyeRight, closedEyeRight);
        setOnMouseClicked(closedEyeRight, openEyeRight);
        setOnMouseClicked(mouth, smile);
        setOnMouseClicked(smile, mouth);
        setOnMouseClicked(blackNose, redNose);
        setOnMouseClicked(redNose, blackNose);


        getChildren().addAll(face, openEyeLeft, openEyeRight, blackNose, mouth);
    }

    private void setOnMouseClicked(Node oldNode, Node newNode) {
        oldNode.setOnMouseClicked(event -> {
            this.getChildren().remove(oldNode);
            this.getChildren().add(newNode);
        });
    }

    private Circle createFace() {
        Circle face = new Circle();
        face.setCenterX(faceCenterX);
        face.setCenterY(faceCenterY);
        face.setRadius(190.0);
        face.setFill(Color.YELLOW);
        face.setStrokeWidth(15.0);
        face.setStroke(Color.BLACK);

        return face;
    }

    private Group createOpenEye(Side side) {
        Circle pupil = new Circle();
        pupil.setCenterY(faceCenterY - eyeYFromFaceCenter + 10);
        pupil.setRadius(15.0);
        pupil.setFill(Color.BLACK);
        pupil.setMouseTransparent(true);

        Circle eye = new Circle();
        eye.setCenterY(faceCenterY - eyeYFromFaceCenter);
        eye.setRadius(30.0);
        eye.setFill(Color.WHITE);
        eye.setStrokeWidth(5.0);
        eye.setStroke(Color.BLACK);

        switch (side) {
            case LEFT:
                eye.setCenterX(faceCenterX - eyeXFromFaceCenter);
                pupil.setCenterX(faceCenterX - eyeXFromFaceCenter + 10);
                break;
            case RIGHT:
                pupil.setCenterX(faceCenterX + eyeXFromFaceCenter - 10);
                eye.setCenterX(faceCenterX + eyeXFromFaceCenter);
                break;
        }

        return new Group(eye, pupil);
    }

    private Line createClosedEye(Side side) {
        Line eye = new Line();
        eye.setStartY(faceCenterY - eyeYFromFaceCenter);
        eye.setEndY(faceCenterY - eyeYFromFaceCenter);
        eye.setStrokeWidth(15.0);
        eye.setStroke(Color.BLACK);

        switch (side) {
            case LEFT:
                eye.setStartX(faceCenterX - eyeXFromFaceCenter - 20);
                eye.setEndX(faceCenterX - eyeXFromFaceCenter + 20);
                break;
            case RIGHT:
                eye.setStartX(faceCenterX + eyeXFromFaceCenter + 20);
                eye.setEndX(faceCenterX + eyeXFromFaceCenter - 20);
                break;
        }

        return eye;
    }

    private Circle createMouth() {
        Circle mouth = new Circle();
        mouth.setCenterX(faceCenterX);
        mouth.setCenterY(faceCenterY + 90);
        mouth.setRadius(30.0);
        mouth.setFill(Color.BLACK);
        return mouth;
    }

    private Arc createSmile() {
        Arc smile = new Arc(faceCenterX, faceCenterY + 90, 100, 60, 180, 180);//(220,310,80,60,0,180);
        smile.setFill(null);
        smile.setStroke(Color.BLACK);
        smile.setStrokeWidth(15.0);
        smile.setType(ArcType.OPEN);
        return smile;
    }

    private Rectangle createNose(Color color) {
        Rectangle nose = new Rectangle();
        nose.setX(faceCenterX);
        nose.setY(faceCenterY + 30);
        nose.setWidth(230);
        nose.setHeight(20);
        nose.setRotate(20);
        nose.setArcWidth(30.0);
        nose.setArcHeight(20.0);
        nose.setFill(color);

        return nose;
    }

    private enum Side {
        LEFT,
        RIGHT
    }
}
