package view;

import ui.Ui;
import model.task.Task;

public class TaskView {
    protected static Ui ui = new Ui();

    public void printTaskDescriptionText(Task model) {
        ui.printMessage(model.getDescriptionText());
    }
}
