package model.interfaces;

public interface ICommand extends IUndoable {
    void run();
}
