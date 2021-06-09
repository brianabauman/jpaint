package model.dialogs;

import model.interfaces.IApplicationState;
import model.ShapeType;
import view.interfaces.IDialogChoice;

public class ChooseShapeDialog implements IDialogChoice<ShapeType> {
    private final IApplicationState applicationState;

    public ChooseShapeDialog(IApplicationState applicationState) {

        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "Shape";
    }

    @Override
    public String getDialogText() {
        return "Select a shape from the menu below:";
    }

    @Override
    public ShapeType[] getDialogOptions() { return new ShapeType[]{ ShapeType.ELLIPSE, ShapeType.RECTANGLE, ShapeType.TRIANGLE }; }

    @Override
    public ShapeType getCurrentSelection() {
        return applicationState.getActiveShapeType();
    }
}
