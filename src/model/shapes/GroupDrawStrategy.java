package model.shapes;

import model.ShapeShadingType;
import model.interfaces.IShapeDrawStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class GroupDrawStrategy implements IShapeDrawStrategy {
    private ArrayList<IShapeDrawStrategy> shapeDrawStrategyArrayList;

    public GroupDrawStrategy(ArrayList<IShapeDrawStrategy> shapeDrawStrategyArrayList) {
        this.shapeDrawStrategyArrayList = shapeDrawStrategyArrayList;
    }

    public void draw(PaintCanvasBase paintCanvas) {
        for (IShapeDrawStrategy shapeDrawStrategy : shapeDrawStrategyArrayList)
            shapeDrawStrategy.draw(paintCanvas);
    }

    public void translate(int deltaX, int deltaY) {
        for (IShapeDrawStrategy shapeDrawStrategy : shapeDrawStrategyArrayList)
            shapeDrawStrategy.translate(deltaX, deltaY);
    }

    public Color getPrimaryColor() { return Color.WHITE; }

    public Color getSecondaryColor() { return Color.WHITE; }

    public ShapeShadingType getShapeShadingType() { return ShapeShadingType.FILLED_IN; }

    public ArrayList<IShapeDrawStrategy> getComponentShapeDrawStrategies() { return shapeDrawStrategyArrayList; }
}
