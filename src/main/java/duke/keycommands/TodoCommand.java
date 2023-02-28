package duke.keycommands;

import duke.common.Common;
import duke.tasktypes.Task;
import duke.tasktypes.ToDo;

/**
 * Represents the command to add a new ToDo task to the task list.
 */
public class TodoCommand {

    private static final String ADDING_TASK = "Got it. I've added this task:";

    private String content;

    /**
     * Constructs a new TodoCommand object with the given content, and adds a new ToDo task to the task list.
     * @param content The content of a ToDo task.
     */
    public TodoCommand(String content) {
        this.content = content;
        addTodoTask();
    }

    private void addTodoTask() {
        Task task = new ToDo(content);
        Common.tasks.add(task);
        System.out.println(ADDING_TASK);
        task.printTask();
        System.out.println("Now you have " + Common.tasks.size() + " tasks in the list.");
        Common.dataFile.appendTaskToDataFile(task.putInputToDataFile());
    }

}
