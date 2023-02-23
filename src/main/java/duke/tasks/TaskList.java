package duke.tasks;

import java.util.ArrayList;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class TaskList extends ArrayList<Task> {

    public void addTask(Task newTask) {
        this.add(newTask);
        newTask.printAdded();
    }

    public void deleteTask(int taskIndex, String taskInfo) {
        this.remove(taskIndex);
        printHorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskInfo);
        taskCount--;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printHorizontalLine();
    }
}
