package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.shapes.ShapeList;

public class MoveShapeCommand implements ICommand {

    private ShapeList shapes;
    private ShapeList selectedShapes;
    private int deltaX;
    private int deltaY;

    public MoveShapeCommand(ShapeList shapes,
                            ShapeList selectedShapes,
                            int deltaX,
                            int deltaY) {
        this.shapes = shapes;
        this.selectedShapes = selectedShapes;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public void run() {
        for (IShape shape : this.shapes)
            for (IShape selectedShape : this.selectedShapes) {
                if (selectedShape.equals(shape)) {
                    shape.translate(this.deltaX, this.deltaY);
                    selectedShape.translate(this.deltaX, this.deltaY);
                }
            }
    }

    public void undo() {
        for (IShape shape : this.shapes)
            for (IShape selectedShape : this.selectedShapes)
                if (selectedShape.equals(shape)) {
                    shape.translate(-1 * this.deltaX, -1 * this.deltaY);
                    selectedShape.translate(-1 * this.deltaX, -1 * this.deltaY);
                }
    }

    public void redo() {
        run();
    }
}
