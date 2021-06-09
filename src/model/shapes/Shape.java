package model.shapes;

import model.interfaces.IShape;
import model.interfaces.IShapeDrawStrategy;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;

public class Shape implements IShape {

    private static int count = 0;
    private String id;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private ShapeType type;
    private IShapeDrawStrategy shapeDrawStrategy;

    public Shape(int x1,
                 int y1,
                 int x2,
                 int y2,
                 ShapeType type,
                 IShapeDrawStrategy shapeDrawStrategy) {
        this.id = "shape" + count++;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.type = type;
        this.shapeDrawStrategy = shapeDrawStrategy;
    }

    public void translate(int deltaX, int deltaY) {
        this.x1 += deltaX;
        this.x2 += deltaX;
        this.y1 += deltaY;
        this.y2 += deltaY;

        this.shapeDrawStrategy.translate(deltaX, deltaY);
    }

    public void draw(PaintCanvasBase paintCanvas) {
        shapeDrawStrategy.draw(paintCanvas);
    }

    public String getID() { return this.id; }

    public ShapeType getType() { return this.type; }

    public int getX1() { return this.x1; }

    public int getY1() { return this.y1; }

    public int getX2() { return this.x2; }

    public int getY2() { return this.y2; }

    public int getWidth() { return Math.abs(this.x2 - this.x1); }

    public int getHeight() { return Math.abs(this.y2 - this.y1); }

    public IShapeDrawStrategy getShapeDrawStrategy() { return this.shapeDrawStrategy; }

    public void setShapeDrawStrategy(IShapeDrawStrategy shapeDrawStrategy) { this.shapeDrawStrategy = shapeDrawStrategy; }

    public ShapeList getComponentShapes() {
        ShapeList shapes = new ShapeList();
        shapes.add(this);
        return shapes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (!(o instanceof IShape)) { return false; }
        IShape otherShape = (IShape)o;

        return this.type == otherShape.getType() &&
                this.x1 == otherShape.getX1() &&
                this.y1 == otherShape.getY1() &&
                this.x2 == otherShape.getX2() &&
                this.y2 == otherShape.getY2();
    }

    @Override
    public String toString() {
        return String.format("ID: %s, P1: (%d, %d), P2: (%d, %d), Type: %s",
                this.id,
                this.x1,
                this.y1,
                this.x2,
                this.y2,
                this.type);
    }
}
