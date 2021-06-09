package model.shapes;

import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;
import model.interfaces.IShapeDrawStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class ShapeDrawStrategyFactory {

    private ShapeDrawStrategyFactory() { }

    public static IShapeDrawStrategy createRegtangleDrawStrategy(int x1,
                                                                 int y1,
                                                                 int x2,
                                                                 int y2,
                                                                 Color primaryColor,
                                                                 Color secondaryColor,
                                                                 ShapeShadingType shadingType) {
        return new RectangleDrawStrategy(Math.min(x1, x2),
                Math.min(y1, y2),
                Math.abs(x2 - x1),
                Math.abs(y2 - y1),
                primaryColor,
                secondaryColor,
                shadingType);
    }

    public static IShapeDrawStrategy createTriangleDrawStrategy(int x1,
                                                                int y1,
                                                                int x2,
                                                                int y2,
                                                                Color primaryColor,
                                                                Color secondaryColor,
                                                                ShapeShadingType shadingType) {

        return new TriangleDrawStrategy(x1,
                y1,
                x2,
                y2,
                primaryColor,
                secondaryColor,
                shadingType);
    }

    public static IShapeDrawStrategy createEllipseDrawStrategy(int x1,
                                                               int y1,
                                                               int x2,
                                                               int y2,
                                                               Color primaryColor,
                                                               Color secondaryColor,
                                                               ShapeShadingType shadingType) {
        return new EllipseDrawStrategy(Math.min(x1, x2),
                Math.min(y1, y2),
                Math.abs(x2 - x1),
                Math.abs(y2 - y1),
                primaryColor,
                secondaryColor,
                shadingType);
    }

    public static IShapeDrawStrategy createOutlinedRectangleDrawStrategy(int x1,
                                                         int y1,
                                                         int x2,
                                                         int y2,
                                                         IShapeDrawStrategy shapeDrawStrategy) {

        return new OutlinedRectangleDrawStrategy(Math.min(x1, x2),
                Math.min(y1, y2),
                Math.abs(x2 - x1),
                Math.abs(y2 - y1),
                shapeDrawStrategy);
    }

    public static IShapeDrawStrategy createOutlinedTriangleDrawStrategy(int x1,
                                                                        int y1,
                                                                        int x2,
                                                                        int y2,
                                                                        IShapeDrawStrategy shapeDrawStrategy) {
        return new OutlinedTriangleDrawStrategy(x1,
                y1,
                x2,
                y2,
                shapeDrawStrategy);
    }

    public static IShapeDrawStrategy createOutlinedEllipseDrawStrategy(int x1,
                                                                       int y1,
                                                                       int x2,
                                                                       int y2,
                                                                       IShapeDrawStrategy shapeDrawStrategy) {
        return new OutlinedEllipseDrawStrategy(Math.min(x1, x2),
                Math.min(y1, y2),
                Math.abs(x2 - x1),
                Math.abs(y2 - y1),
                shapeDrawStrategy);
    }

    public static IShapeDrawStrategy createGroupDrawStrategy(ArrayList<IShapeDrawStrategy> shapeDrawStrategyArrayList) {
        return new GroupDrawStrategy(shapeDrawStrategyArrayList);
    }

    public static IShapeDrawStrategy createOutlinedGroupDrawStrategy(int x1,
                                                                     int y1,
                                                                     int x2,
                                                                     int y2,
                                                                     IShapeDrawStrategy shapeDrawStrategy) {
        return new OutlinedGroupDrawStrategy(x1,
                y1,
                x2,
                y2,
                shapeDrawStrategy);
    }

    public static IShapeDrawStrategy copyShapeDrawStrategy(IShape shape) {
        IShapeDrawStrategy shapeDrawStrategy;

        switch(shape.getType()) {
            case RECTANGLE:
                shapeDrawStrategy = createRegtangleDrawStrategy(shape.getX1(),
                        shape.getY1(),
                        shape.getX2(),
                        shape.getY2(),
                        shape.getShapeDrawStrategy().getPrimaryColor(),
                        shape.getShapeDrawStrategy().getSecondaryColor(),
                        shape.getShapeDrawStrategy().getShapeShadingType());
                break;
            case ELLIPSE:
                shapeDrawStrategy = createEllipseDrawStrategy(shape.getX1(),
                        shape.getY1(),
                        shape.getX2(),
                        shape.getY2(),
                        shape.getShapeDrawStrategy().getPrimaryColor(),
                        shape.getShapeDrawStrategy().getSecondaryColor(),
                        shape.getShapeDrawStrategy().getShapeShadingType());
                break;
            case TRIANGLE:
                shapeDrawStrategy = createTriangleDrawStrategy(shape.getX1(),
                        shape.getY1(),
                        shape.getX2(),
                        shape.getY2(),
                        shape.getShapeDrawStrategy().getPrimaryColor(),
                        shape.getShapeDrawStrategy().getSecondaryColor(),
                        shape.getShapeDrawStrategy().getShapeShadingType());
                break;
            case GROUP:
                ArrayList<IShapeDrawStrategy> shapeDrawStrategyArrayList = new ArrayList<IShapeDrawStrategy>();

                for (IShape thisShape : shape.getComponentShapes()) {
                    shapeDrawStrategyArrayList.add(copyShapeDrawStrategy(thisShape));
                }

                shapeDrawStrategy = createGroupDrawStrategy(shapeDrawStrategyArrayList);
                break;
            default:
                shapeDrawStrategy = null;
                break;
        }

        return shapeDrawStrategy;
    }
}
