package model.shapes;

import model.ShapeShadingType;
import model.interfaces.IShapeDrawStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class OutlinedGroupDrawStrategy implements IShapeDrawStrategy {
    int OUTLINE_WIDTH = 3;
    int SELECTED_OUTLINE_OFFSET = 5;

    private int x1;
    private int y1;
    private int width;
    private int height;
    private IShapeDrawStrategy shapeDrawStrategy;

    public OutlinedGroupDrawStrategy(int x1,
                                     int y1,
                                     int x2,
                                     int y2,
                                     IShapeDrawStrategy shapeDrawStrategy) {
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.width = Math.abs(x2 - x1);
        this.height = Math.abs(y2 - y1);
        this.shapeDrawStrategy = shapeDrawStrategy;
    }

    public void draw(PaintCanvasBase paintCanvas) {
        shapeDrawStrategy.draw(paintCanvas);

        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(OUTLINE_WIDTH,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL,
                0,
                new float[]{ 9 },
                0));
        graphics2D.drawRect(this.x1 - SELECTED_OUTLINE_OFFSET,
                this.y1 - SELECTED_OUTLINE_OFFSET,
                this.width + 2 * SELECTED_OUTLINE_OFFSET,
                this.height + 2 * SELECTED_OUTLINE_OFFSET);
    }

    public void translate(int deltaX, int deltaY) {
        shapeDrawStrategy.translate(deltaX, deltaY);

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
