package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.shapes.ShapeFactory;
import model.shapes.ShapeList;

public class DrawShapeCommand implements ICommand {
    private ShapeList shapes;
    private ShapeList selectedShapes;
    private IShape shape;
    private ShapeList originalSelectedShapes;

    public DrawShapeCommand(ShapeList shapes,
                            ShapeList selectedShapes,
                            IShape shape) {
        this.shapes = shapes;
        this.selectedShapes = selectedShapes;
        this.shape = shape;
        this.originalSelectedShapes = new ShapeList();
    }

    public void run() {
        this.selectedShapes.removeAll(this.selectedShapes);
        this.shapes.add(this.shape);
    }

    public void undo() {
        for (IShape shape : this.selectedShapes)
            this.originalSelectedShapes.add(ShapeFactory.copyShape(shape));

        selectedShapes.removeAll(this.selectedShapes);
        this.shapes.remove(this.shape);
    }

    public void redo() {
        run();
        this.selectedShapes.addAll(this.originalSelectedShapes);
        this.originalSelectedShapes = new ShapeList();
    }
}
