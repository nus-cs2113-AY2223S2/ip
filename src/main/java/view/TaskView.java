package view;

import model.task.Task;

public class TaskView {
    public void printTaskDescriptionText(Task model) {
        System.out.println(model.getDescriptionText());
    }
}
