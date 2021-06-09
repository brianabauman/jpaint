package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.shapes.ShapeFactory;
import model.shapes.ShapeList;

public class GroupShapesCommand implements ICommand {

    private ShapeList shapes;
    private ShapeList originalShapes;
    private ShapeList selectedShapes;
    private ShapeList originalSelectedShapes;

    public GroupShapesCommand(ShapeList shapes, ShapeList selectedShapes) {
        this.shapes = shapes;
        this.selectedShapes = selectedShapes;

        this.originalShapes = new ShapeList();
        for (IShape shape : shapes)
            originalShapes.add(ShapeFactory.copyShape(shape));

        this.originalSelectedShapes = new ShapeList();
        for (IShape selectedShape : selectedShapes)
            originalSelectedShapes.add(ShapeFactory.copyShape(selectedShape));
    }

    public void run() {
        IShape shapeGroup = ShapeFactory.createShapeGroup(selectedShapes);
        shapes.removeAll(selectedShapes);
        shapes.add(shapeGroup);

        selectedShapes.removeAll(selectedShapes);
        selectedShapes.add(ShapeFactory.copyShape(shapeGroup));
    }

    public void undo() {
        shapes.removeAll(shapes);
        shapes.addAll(originalShapes);
        selectedShapes.removeAll(selectedShapes);
        selectedShapes.addAll(originalSelectedShapes);
    }

    public void redo() {
        run();
    }
}
