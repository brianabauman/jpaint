package model.shapes;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import model.interfaces.IShapeDrawStrategy;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class ShapeFactory {

    public static IShape createShape(int x1,
                              int y1,
                              int x2,
                              int y2,
                              ShapeType type,
                              ShapeColor primaryColor,
                              ShapeColor secondaryColor,
                              ShapeShadingType shadingType) {
        IShapeDrawStrategy shapeDrawStrategy;
        switch(type) {
            case RECTANGLE:
                shapeDrawStrategy = ShapeDrawStrategyFactory.createRegtangleDrawStrategy(x1,
                        y1,
                        x2,
                        y2,
                        primaryColor.getColor(),
                        secondaryColor.getColor(),
                        shadingType);
                break;
            case TRIANGLE:
                shapeDrawStrategy = ShapeDrawStrategyFactory.createTriangleDrawStrategy(x1,
                        y1,
                        x2,
                        y2,
                        primaryColor.getColor(),
                        secondaryColor.getColor(),
                        shadingType);

                break;
            case ELLIPSE:
                shapeDrawStrategy = ShapeDrawStrategyFactory.createEllipseDrawStrategy(x1,
                        y1,
                        x2,
                        y2,
                        primaryColor.getColor(),
                        secondaryColor.getColor(),
                        shadingType);
                break;
            case GROUP:
                shapeDrawStrategy = ShapeDrawStrategyFactory.createGroupDrawStrategy(new ArrayList<IShapeDrawStrategy>());
                break;
            default:
                shapeDrawStrategy = null;
                break;
        }

        return new Shape(x1,
                y1,
                x2,
                y2,
                type,
                shapeDrawStrategy);
    }

    public static IShape copyShape(IShape shape) {
        IShape returnShape;

        if (shape.getType() == ShapeType.GROUP) {
            ShapeList newShapes = new ShapeList();
            for (IShape thisShape : shape.getComponentShapes())
                newShapes.add(copyShape(thisShape));

            returnShape = createShapeGroup(newShapes);
        } else {
            returnShape = new Shape(shape.getX1(),
                    shape.getY1(),
                    shape.getX2(),
                    shape.getY2(),
                    shape.getType(),
                    ShapeDrawStrategyFactory.copyShapeDrawStrategy(shape));
        }

        return returnShape;
    }

    public static IShape createShapeGroup(ShapeList shapes) {

        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;
        ShapeList newShapes = new ShapeList();
        ArrayList<IShapeDrawStrategy> shapeDrawStrategyArrayList = new ArrayList<>();
        for (IShape shape : shapes) {
            int minX = Math.min(shape.getX1(), shape.getX2());
            int minY = Math.min(shape.getY1(), shape.getY2());
            int maxX = Math.max(shape.getX1(), shape.getX2());
            int maxY = Math.max(shape.getY1(), shape.getY2());
            if (minX < x1) { x1 = minX; }
            if (minY < y1) { y1 = minY; }
            if (maxX > x2) { x2 = maxX; }
            if (maxY > y2) { y2 = maxY; }
            IShape newShape = copyShape(shape);
            newShapes.add(newShape);
            shapeDrawStrategyArrayList.add(ShapeDrawStrategyFactory.copyShapeDrawStrategy(newShape));
        }
        IShapeDrawStrategy shapeDrawStrategy = ShapeDrawStrategyFactory.createGroupDrawStrategy(shapeDrawStrategyArrayList);

        return new ShapeGroup(x1,
                y1,
                x2,
                y2,
                newShapes,
                shapeDrawStrategy);
    }
}
