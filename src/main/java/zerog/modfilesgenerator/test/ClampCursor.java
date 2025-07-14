package zerog.modfilesgenerator.test;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClampCursor extends Thread{

    private Circle circle;
    private AnchorPane anchorPane;

    public ClampCursor(Circle circle, AnchorPane anchorPane){
        this.circle = circle;
        this.anchorPane = anchorPane;
    }

    @Override
    public void run() {
        for(;;){
            double x = clamp(MouseInfo.getPointerInfo().getLocation().x, anchorPane.getLayoutBounds().getMinX(), anchorPane.getLayoutBounds().getMaxX());
            double y = clamp(MouseInfo.getPointerInfo().getLocation().y, anchorPane.getLayoutBounds().getMinY(), anchorPane.getLayoutBounds().getMaxY());
            circle.setCenterX(x);
            circle.setCenterY(y);
            //System.out.println("Set center to " + x + " " + y+"; Mouse cords: "+MouseInfo.getPointerInfo().getLocation().x+" "+MouseInfo.getPointerInfo().getLocation().y);
        }
    }

    private static double clamp(double num, double min, double max){
        return Math.min(Math.max(num, min), max);
    }
}
