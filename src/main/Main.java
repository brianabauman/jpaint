package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.MouseHandler;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class Main {
    public static void main(String[] args){
        PaintCanvas paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        MouseHandler mouseHandler = new MouseHandler(paintCanvas, appState);
        paintCanvas.addMouseListener(mouseHandler);
        IJPaintController controller = new JPaintController(uiModule, appState, paintCanvas);
        controller.setup();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Filled in rectangle
        //Graphics2D graphics2d = paintCanvas.getGraphics2D();
        //System.out.println(graphics2d.toString());
        //graphics2d.setColor(Color.GREEN);
        //graphics2d.fillRect(12, 13, 200, 400);
    }
}
