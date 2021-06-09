package model.interfaces;

import model.ShapeType;
import model.shapes.ShapeList;
import view.interfaces.PaintCanvasBase;

public interface IShape {
    void translate(int deltaX, int deltaY);

    void draw(PaintCanvasBase paintCanvas);

    String getID();

    ShapeType getType();

    int getX1();

    int getY1();

    int getX2();

    int getY2();

    int getWidth();

    int getHeight();

    IShapeDrawStrategy getShapeDrawStrategy();

    void setShapeDrawStrategy(IShapeDrawStrategy shapeDrawStrategy);

    ShapeList getComponentShapes();
}
