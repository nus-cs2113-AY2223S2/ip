package view;

import model.task.Task;
import ui.Ui;

public class TaskView {
    protected static Ui ui = Ui.getInstance();

    public void printTaskDescriptionText(Task model) {
        ui.printMessage(model.getDescriptionText());
    }
}
