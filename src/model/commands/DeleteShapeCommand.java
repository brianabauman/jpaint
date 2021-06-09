package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.shapes.Shape;
import model.shapes.ShapeFactory;
import model.shapes.ShapeList;

public class DeleteShapeCommand implements ICommand {

    private ShapeList shapes;
    private ShapeList selectedShapes;
    private ShapeList originalSelectedShapes;

    public DeleteShapeCommand(ShapeList shapes, ShapeList selectedShapes) {
        this.shapes = shapes;
        this.selectedShapes = selectedShapes;

        originalSelectedShapes = new ShapeList();
        for(IShape shape : selectedShapes)
            originalSelectedShapes.add(ShapeFactory.copyShape(shape));
    }

    public void run() {
        shapes.removeAll(selectedShapes);
        selectedShapes.removeAll(selectedShapes);
    }

    public void undo() {
        shapes.addAll(originalSelectedShapes);

        selectedShapes.removeAll(selectedShapes);
        for (IShape shape : originalSelectedShapes) {
            selectedShapes.add(ShapeFactory.copyShape(shape));
        }
    }

    public void redo() {
        run();
    }
}
