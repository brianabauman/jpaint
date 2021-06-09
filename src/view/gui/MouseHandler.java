package view.gui;

import model.commands.CommandHistory;
import model.commands.DrawShapeCommand;
import model.commands.MoveShapeCommand;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import model.shapes.ShapeFactory;
import model.shapes.ShapeList;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    private int x1 = 0;
    private int y1 = 0;
    private int x2 = 0;
    private int y2 = 0;
    private PaintCanvas paintCanvas;
    private ApplicationState appState;

    public MouseHandler(PaintCanvas paintCanvas, ApplicationState appState) {
        this.paintCanvas = paintCanvas;
        this.appState = appState;
    }

    public void mousePressed (MouseEvent mouseEvent) {
        this.x1 = mouseEvent.getX();
        this.y1 = mouseEvent.getY();
    }

    public void mouseReleased (MouseEvent mouseEvent) {
        this.x2 = mouseEvent.getX();
        this.y2 = mouseEvent.getY();

        switch (appState.getActiveStartAndEndPointMode()) {
            case DRAW:
                IShape shape = ShapeFactory.createShape(this.x1,
                        this.y1,
                        this.x2,
                        this.y2,
                        appState.getActiveShapeType(),
                        appState.getActivePrimaryColor(),
                        appState.getActiveSecondaryColor(),
                        appState.getActiveShapeShadingType());

                ICommand drawShapeCommand = new DrawShapeCommand(appState.getShapes(),
                        appState.getSelectedShapes(),
                        shape);
                CommandHistory.add(drawShapeCommand);
                drawShapeCommand.run();

                paintCanvas.redraw(appState.getShapes(), appState.getSelectedShapes());
                break;
            case SELECT:
                int selectionX1 = Math.min(this.x1, this.x2);
                int selectionY1 = Math.min(this.y1, this.y2);
                int selectionWidth = Math.abs(this.x2 - this.x1);
                int selectionHeight = Math.abs(this.y2 - this.y1);

                appState.getSelectedShapes().removeAll(appState.getSelectedShapes());

                for (IShape thisShape : appState.getShapes()) {
                    int shapeX1 = Math.min(thisShape.getX1(), thisShape.getX2());
                    int shapeY1 = Math.min(thisShape.getY1(), thisShape.getY2());
                    int shapeWidth = thisShape.getWidth();
                    int shapeHeight = thisShape.getHeight();

                    if (selectionX1 < shapeX1 + shapeWidth &&
                            selectionX1 + selectionWidth > shapeX1 &&
                            selectionY1 < shapeY1 + shapeHeight &&
                            selectionY1 + selectionHeight > shapeY1) {
                        appState.getSelectedShapes().add(ShapeFactory.copyShape(thisShape));
                    }
                }

                paintCanvas.redraw(appState.getShapes(), appState.getSelectedShapes());
                break;
            case MOVE:
                int deltaX = this.x2 - this.x1;
                int deltaY = this.y2 - this.y1;

                if (appState.getSelectedShapes().size() > 0) {
                    ICommand moveShapeCommand = new MoveShapeCommand(appState.getShapes(),
                            appState.getSelectedShapes(),
                            deltaX,
                            deltaY);
                    CommandHistory.add(moveShapeCommand);
                    moveShapeCommand.run();

                    paintCanvas.redraw(appState.getShapes(), appState.getSelectedShapes());
                }
                break;
        }
    }

    public void mouseClicked (MouseEvent mouseEvent) {}

    public void mouseEntered (MouseEvent mouseEvent) {}

    public void mouseExited (MouseEvent mouseEvent) {}
}
