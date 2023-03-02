package duke.Commands;

import duke.Storage;
import duke.TaskList;
import dukeException.DukeException;
import tasks.Task;

import static duke.Duke.LINE_SPACING;

public class Command {
    protected TaskList tasks = new TaskList();
    protected boolean isExit = false;
    public static void addTaskPrint(TaskList tasks, Task tsk) {
        System.out.println(LINE_SPACING);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tsk.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
        System.out.println(LINE_SPACING);
        Storage.saveTasks(tasks);

    }
    public Task getTask(int idx) {
        return tasks.getTask(idx);
    }
    public void cmd() throws DukeException {
        System.out.println("Please enter in a valid command");
    }

    public boolean isExit() {
        return isExit;
    }
}
