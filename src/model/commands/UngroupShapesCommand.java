package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.shapes.ShapeFactory;
import model.shapes.ShapeList;

public class UngroupShapesCommand implements ICommand {
    private ShapeList shapes;
    private ShapeList originalShapes;
    private ShapeList selectedShapes;
    private ShapeList newlySelectedShapes;
    private ShapeList originalSelectedShapes;

    public UngroupShapesCommand(ShapeList shapes, ShapeList selectedShapes) {
        this.shapes = shapes;
        this.selectedShapes = selectedShapes;

        this.newlySelectedShapes = new ShapeList();

        this.originalShapes = new ShapeList();
        for (IShape shape : shapes)
            originalShapes.add(ShapeFactory.copyShape(shape));

        this.originalSelectedShapes = new ShapeList();
        for (IShape selectedShape : selectedShapes)
            originalSelectedShapes.add(ShapeFactory.copyShape(selectedShape));
    }

    public void run() {
        for (IShape selectedShape : this.selectedShapes) {
            this.shapes.remove(selectedShape);
            this.shapes.addAll(selectedShape.getComponentShapes());

            for (IShape componentShape : selectedShape.getComponentShapes()) {
                newlySelectedShapes.add(ShapeFactory.copyShape(componentShape));
            }
        }

        this.selectedShapes.removeAll(this.selectedShapes);
        this.selectedShapes.addAll(this.newlySelectedShapes);
    }

    public void undo() {
        this.shapes.removeAll(this.shapes);
        this.shapes.addAll(this.originalShapes);
        this.selectedShapes.removeAll(this.selectedShapes);
        this.selectedShapes.addAll(this.originalSelectedShapes);
    }

    public void redo() {
        run();
    }
}
