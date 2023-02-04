package view;

import model.task.Task;

public class TaskView {
    public void printTaskDescriptionText(Task task) {
        System.out.println(task.getDescriptionText());
    }
}
