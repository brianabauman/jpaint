package view.gui;

import model.interfaces.IShape;
import model.interfaces.IShapeDrawStrategy;
import model.shapes.ShapeDrawStrategyFactory;
import model.shapes.ShapeList;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class PaintCanvas extends PaintCanvasBase {

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    public void redraw(ShapeList shapes, ShapeList selectedShapes) {
        this.paintImmediately(0,
                0,
                this.getWidth(),
                this.getHeight());

        for (IShape shape : shapes) {
            shape.draw(this);
        }

        for (IShape selectedShape : selectedShapes) {
            IShapeDrawStrategy outlineShapeDrawStrategy;

            switch (selectedShape.getType()) {
                case RECTANGLE:
                    outlineShapeDrawStrategy = ShapeDrawStrategyFactory.createOutlinedRectangleDrawStrategy(selectedShape.getX1(),
                            selectedShape.getY1(),
                            selectedShape.getX2(),
                            selectedShape.getY2(),
                            selectedShape.getShapeDrawStrategy());
                    break;
                case TRIANGLE:
                    outlineShapeDrawStrategy = ShapeDrawStrategyFactory.createOutlinedTriangleDrawStrategy(selectedShape.getX1(),
                            selectedShape.getY1(),
                            selectedShape.getX2(),
                            selectedShape.getY2(),
                            selectedShape.getShapeDrawStrategy());
                    break;
                case ELLIPSE:
                    outlineShapeDrawStrategy = ShapeDrawStrategyFactory.createOutlinedEllipseDrawStrategy(selectedShape.getX1(),
                            selectedShape.getY1(),
                            selectedShape.getX2(),
                            selectedShape.getY2(),
                            selectedShape.getShapeDrawStrategy());
                    break;
                case GROUP:
                    outlineShapeDrawStrategy = ShapeDrawStrategyFactory.createOutlinedGroupDrawStrategy(selectedShape.getX1(),
                            selectedShape.getY1(),
                            selectedShape.getX2(),
                            selectedShape.getY2(),
                            selectedShape.getShapeDrawStrategy());
                    break;
                default:
                    outlineShapeDrawStrategy = null;
                    break;
            }
            selectedShape.setShapeDrawStrategy(outlineShapeDrawStrategy);
            selectedShape.draw(this);
        }
    }
}
