package view;

import model.task.Task;
import ui.Ui;

public class TaskView {
    protected static Ui ui = new Ui();

    public void printTaskDescriptionText(Task model) {
        ui.printMessage(model.getDescriptionText());
    }
}
