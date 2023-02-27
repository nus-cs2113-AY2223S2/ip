package duke.keycommands;

import duke.common.Common;
import duke.tasktypes.Task;
import duke.tasktypes.ToDo;

public class TodoCommand {

    private static final String EMPTY_TODO_DESCRIPTION = "OOPS!!! The description of a todo cannot be empty.";
    private static final String EMPTY_TODO_TASK = "OOPS!!! your task can not be empty";
    private static final String ADDING_TASK = "Got it. I've added this task:";

    private String content;

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
