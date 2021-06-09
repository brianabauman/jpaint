package model.shapes;

import model.ShapeShadingType;
import model.interfaces.IShapeDrawStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class OutlinedEllipseDrawStrategy implements IShapeDrawStrategy {
    int OUTLINE_WIDTH = 3;
    int SELECTED_OUTLINE_OFFSET = 5;

    private int x1;
    private int y1;
    private int width;
    private int height;
    private IShapeDrawStrategy shapeDrawStrategy;

    public OutlinedEllipseDrawStrategy(int x1,
                                       int y1,
                                       int width,
                                       int height,
                                       IShapeDrawStrategy shapeDrawStrategy) {
        this.x1 = x1;
        this.y1 = y1;
        this.width = width;
        this.height = height;
        this.shapeDrawStrategy = shapeDrawStrategy;
    }

    public void draw(PaintCanvasBase paintCanvas) {
        this.shapeDrawStrategy.draw(paintCanvas);

        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(OUTLINE_WIDTH,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL,
                0,
                new float[]{ 9 },
                0));
        graphics2D.drawOval(x1 - SELECTED_OUTLINE_OFFSET,
                y1 - SELECTED_OUTLINE_OFFSET,
                this.width + 2 * SELECTED_OUTLINE_OFFSET,
                this.height + 2 * SELECTED_OUTLINE_OFFSET);
    }

    public void translate(int deltaX, int deltaY) {
        this.shapeDrawStrategy.translate(deltaX, deltaY);

        this.x1 += deltaX;
        this.y1 += deltaY;
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
