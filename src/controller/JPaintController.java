package controller;

import model.commands.*;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import view.EventName;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final PaintCanvas paintCanvas;

    public JPaintController(IUiModule uiModule,
                            IApplicationState applicationState,
                            PaintCanvas paintCanvas) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.COPY, () -> applicationState.setCopiedShapes(applicationState.getSelectedShapes()));
        uiModule.addEvent(EventName.PASTE, () -> {
            ICommand pasteShapeCommand = new PasteShapeCommand(applicationState.getShapes(), applicationState.getCopiedShapes());
            CommandHistory.add(pasteShapeCommand);
            pasteShapeCommand.run();


            paintCanvas.redraw(applicationState.getShapes(), applicationState.getSelectedShapes());
        });
        uiModule.addEvent(EventName.DELETE, () -> {
            if (applicationState.getSelectedShapes().size() > 0) {
                ICommand deleteShapeCommand = new DeleteShapeCommand(applicationState.getShapes(), applicationState.getSelectedShapes());
                CommandHistory.add(deleteShapeCommand);
                deleteShapeCommand.run();
            }
            paintCanvas.redraw(applicationState.getShapes(), applicationState.getSelectedShapes());
        });
        uiModule.addEvent(EventName.GROUP, () -> {
            if (applicationState.getSelectedShapes().size() > 1) {
                ICommand groupShapeCommand = new GroupShapesCommand(applicationState.getShapes(), applicationState.getSelectedShapes());
                CommandHistory.add(groupShapeCommand);
                groupShapeCommand.run();

                paintCanvas.redraw(applicationState.getShapes(), applicationState.getSelectedShapes());
            }
        });
        uiModule.addEvent(EventName.UNGROUP, () -> {
            if (applicationState.getSelectedShapes().size() > 0) {
                ICommand ungroupShapeCommand = new UngroupShapesCommand(applicationState.getShapes(), applicationState.getSelectedShapes());
                CommandHistory.add(ungroupShapeCommand);
                ungroupShapeCommand.run();

                paintCanvas.redraw(applicationState.getShapes(), applicationState.getSelectedShapes());
            }
        });
        uiModule.addEvent(EventName.UNDO, () -> {
            CommandHistory.undo();

            paintCanvas.redraw(applicationState.getShapes(), applicationState.getSelectedShapes());
        });
        uiModule.addEvent(EventName.REDO, () -> {
            CommandHistory.redo();

            paintCanvas.redraw(applicationState.getShapes(), applicationState.getSelectedShapes());
        });
    }
}
