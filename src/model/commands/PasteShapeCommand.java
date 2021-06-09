package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.shapes.ShapeFactory;
import model.shapes.ShapeList;

public class PasteShapeCommand implements ICommand {

    private int PASTE_OFFSET = 20;
    private ShapeList shapes;
    private ShapeList copiedShapes;
    private ShapeList pastedShapes;

    public PasteShapeCommand(ShapeList shapes, ShapeList copiedShapes) {
        this.shapes = shapes;
        this.copiedShapes = copiedShapes;
        this.pastedShapes = new ShapeList();
    }

    public void run() {
        for (IShape shape : copiedShapes) {
            IShape newShape = ShapeFactory.copyShape(shape);
            newShape.translate(PASTE_OFFSET, PASTE_OFFSET);
            pastedShapes.add(newShape);
            shapes.add(newShape);
        }
    }

    public void undo() {
        for (IShape shape : shapes)
            shapes.removeAll(pastedShapes);

        pastedShapes = new ShapeList();
    }

    public void redo() {
        run();
    }
}
