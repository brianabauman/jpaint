package model.shapes;

import model.interfaces.IShapeDrawStrategy;
import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class RectangleDrawStrategy implements IShapeDrawStrategy {
    int OUTLINE_WIDTH = 3;

    private int x1;
    private int y1;
    private int width;
    private int height;
    private Color primaryColor;
    private Color secondaryColor;
    private ShapeShadingType shadingType;

    public RectangleDrawStrategy(int x1,
                                 int y1,
                                 int width,
                                 int height,
                                 Color primaryColor,
                                 Color secondaryColor,
                                 ShapeShadingType shadingType) {
        this.x1 = x1;
        this.y1 = y1;
        this.width = width;
        this.height = height;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shadingType = shadingType;
    }

    public void draw(PaintCanvasBase paintCanvas) {
        Graphics2D graphics2D = paintCanvas.getGraphics2D();

        switch (this.shadingType) {
            case FILLED_IN:
                graphics2D.setColor(this.primaryColor);
                graphics2D.fillRect(this.x1,
                        this.y1,
                        this.width,
                        this.height);
                break;
            case OUTLINE:
                graphics2D.setColor(this.primaryColor);
                graphics2D.setStroke(new BasicStroke(OUTLINE_WIDTH));
                graphics2D.drawRect(this.x1,
                        this.y1,
                        this.width,
                        this.height);
                break;
            case OUTLINE_AND_FILLED_IN:
                graphics2D.setColor(this.primaryColor);
                graphics2D.fillRect(this.x1,
                        this.y1,
                        this.width,
                        this.height);

                graphics2D.setColor(this.secondaryColor);
                graphics2D.setStroke(new BasicStroke(OUTLINE_WIDTH));
                graphics2D.drawRect(this.x1,
                        this.y1,
                        this.width,
                        this.height);
                break;
            default:
                break;
        }
    }

    public void translate(int deltaX, int deltaY) {
        this.x1 += deltaX;
        this.y1 += deltaY;
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
