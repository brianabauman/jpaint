package model.shapes;

import model.ShapeShadingType;
import model.interfaces.IShapeDrawStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class OutlinedTriangleDrawStrategy implements IShapeDrawStrategy {
    int OUTLINE_WIDTH = 3;
    int SELECTED_OUTLINE_OFFSET = 5;

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int[] xPoints;
    private int[] yPoints;
    private IShapeDrawStrategy shapeDrawStrategy;

    public OutlinedTriangleDrawStrategy(int x1,
                                        int y1,
                                        int x2,
                                        int y2,
                                        IShapeDrawStrategy shapeDrawStrategy) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.xPoints = new int[]{ x1, x2, x1 };
        this.yPoints = new int[]{ y1, y2, y2 };
        this.shapeDrawStrategy = shapeDrawStrategy;
    }

    public void draw(PaintCanvasBase paintCanvas) {
        this.shapeDrawStrategy.draw(paintCanvas);

        int[] outlineXPoints = getOutlineXPoints();
        int[] outlineYPoints = getOutlineYPoints();

        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(OUTLINE_WIDTH,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL,
                0,
                new float[]{ 9 },
                0));
        graphics2D.drawPolygon(outlineXPoints,
                outlineYPoints,
                3);
    }

    public void translate(int deltaX, int deltaY) {
        this.shapeDrawStrategy.translate(deltaX, deltaY);

        this.xPoints[0] += deltaX;
        this.xPoints[1] += deltaX;
        this.xPoints[2] += deltaX;
        this.yPoints[0] += deltaY;
        this.yPoints[1] += deltaY;
        this.yPoints[2] += deltaY;
    }

    private int[] getOutlineXPoints() {
        int[] outlineXPoints;

        if (this.xPoints[0] < this.xPoints[1]) {
            outlineXPoints = new int[]{ this.xPoints[0] - SELECTED_OUTLINE_OFFSET,
                    this.xPoints[1] + 2 * SELECTED_OUTLINE_OFFSET,
                    this.xPoints[2] - SELECTED_OUTLINE_OFFSET };
        } else {
            outlineXPoints = new int[]{ xPoints[0] + SELECTED_OUTLINE_OFFSET,
                    this.xPoints[1] - 2 * SELECTED_OUTLINE_OFFSET,
                    this.xPoints[2] + SELECTED_OUTLINE_OFFSET };
        }

        return outlineXPoints;
    }

    private int[] getOutlineYPoints() {
        int[] outlineYPoints;

        if (this.yPoints[0] < this.yPoints[1]) {
            outlineYPoints = new int[]{this.yPoints[0] - 2 * SELECTED_OUTLINE_OFFSET,
                    this.yPoints[1] + SELECTED_OUTLINE_OFFSET,
                    this.yPoints[2] + SELECTED_OUTLINE_OFFSET};
        } else {
            outlineYPoints = new int[]{this.yPoints[0] + 2 * SELECTED_OUTLINE_OFFSET,
                    this.yPoints[1] - SELECTED_OUTLINE_OFFSET,
                    this.yPoints[2] - SELECTED_OUTLINE_OFFSET};
        }

        return outlineYPoints;
    }

    public Color getPrimaryColor() { return shapeDrawStrategy.getPrimaryColor(); }

    public Color getSecondaryColor() { return shapeDrawStrategy.getSecondaryColor(); }

    public ShapeShadingType getShapeShadingType() { return shapeDrawStrategy.getShapeShadingType(); }

    public ArrayList<IShapeDrawStrategy> getComponentShapeDrawStrategies() {
        ArrayList<IShapeDrawStrategy> shapeDrawStrategyArrayList = new ArrayList<IShapeDrawStrategy>();
        shapeDrawStrategyArrayList.add(this);
        return shapeDrawStrategyArrayList;
    }
}
