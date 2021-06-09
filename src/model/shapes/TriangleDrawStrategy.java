package model.shapes;

import model.interfaces.IShape;
import model.interfaces.IShapeDrawStrategy;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class TriangleDrawStrategy implements IShapeDrawStrategy {
    int OUTLINE_WIDTH = 3;

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int[] xPoints;
    private int[] yPoints;
    private Color primaryColor;
    private Color secondaryColor;
    private ShapeShadingType shadingType;

    public TriangleDrawStrategy(int x1,
                                int y1,
                                int x2,
                                int y2,
                                Color primaryColor,
                                Color secondaryColor,
                                ShapeShadingType shadingType) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.xPoints = new int[]{ x1, x2, x1 };
        this.yPoints = new int[]{ y1, y2, y2 };
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shadingType = shadingType;
    }

    public void draw(PaintCanvasBase paintCanvas) {
        Graphics2D graphics2D = paintCanvas.getGraphics2D();

        switch (this.shadingType) {
            case FILLED_IN:
                graphics2D.setColor(this.primaryColor);
                graphics2D.fillPolygon(this.xPoints,
                        this.yPoints,
                        3);
                break;
            case OUTLINE:
                graphics2D.setColor(this.primaryColor);
                graphics2D.setStroke(new BasicStroke(OUTLINE_WIDTH));
                graphics2D.drawPolygon(this.xPoints,
                        this.yPoints,
                        3);
                break;
            case OUTLINE_AND_FILLED_IN:
                graphics2D.setColor(this.primaryColor);
                graphics2D.fillPolygon(this.xPoints,
                        this.yPoints,
                        3);

                graphics2D.setColor(this.secondaryColor);
                graphics2D.setStroke(new BasicStroke(OUTLINE_WIDTH));
                graphics2D.drawPolygon(this.xPoints,
                        this.yPoints,
                        3);
                break;
            default:
                break;
        }
    }

    public void translate(int deltaX, int deltaY) {
        this.xPoints[0] += deltaX;
        this.xPoints[1] += deltaX;
        this.xPoints[2] += deltaX;
        this.yPoints[0] += deltaY;
        this.yPoints[1] += deltaY;
        this.yPoints[2] += deltaY;
    }

    public Color getPrimaryColor() { return this.primaryColor; };

    public Color getSecondaryColor() { return this.secondaryColor; }

    public ShapeShadingType getShapeShadingType() { return this.shadingType; }

    public ArrayList<IShapeDrawStrategy> getComponentShapeDrawStrategies() {
        ArrayList<IShapeDrawStrategy> shapeDrawStrategyArrayList = new ArrayList<IShapeDrawStrategy>();
        shapeDrawStrategyArrayList.add(this);
        return shapeDrawStrategyArrayList;
    }

}
