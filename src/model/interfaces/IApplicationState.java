package model.interfaces;

import model.shapes.ShapeList;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;

public interface IApplicationState {

    void setShapes(ShapeList shapes);

    void setSelectedShapes(ShapeList shapes);

    void setCopiedShapes(ShapeList shapes);

    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndEndPointMode();

    ShapeList getShapes();

    ShapeList getSelectedShapes();

    ShapeList getCopiedShapes();

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    StartAndEndPointMode getActiveStartAndEndPointMode();
}
