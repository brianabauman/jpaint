package model.interfaces;

import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public interface IShapeDrawStrategy {
    void draw(PaintCanvasBase paintCanvas);

    void translate(int deltaX, int deltaY);

    Color getPrimaryColor();

    Color getSecondaryColor();

    ShapeShadingType getShapeShadingType();

    ArrayList<IShapeDrawStrategy> getComponentShapeDrawStrategies();
}
