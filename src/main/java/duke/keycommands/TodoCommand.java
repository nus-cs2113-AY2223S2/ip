package duke.keycommands;

import duke.common.Common;
import duke.tasktypes.Task;
import duke.exception.EmptyDescription;
import duke.tasktypes.ToDo;

public class TodoCommand {
    private String[] separatedKeyWordAndContent;
    private static final String EMPTY_TODO_DESCRIPTION = "OOPS!!! The description of a todo cannot be empty.";
    private static final String EMPTY_TODO_TASK = "OOPS!!! your task can not be empty";
    private static final String ADDING_TASK = "Got it. I've added this task:";

    public TodoCommand(String[] separatedKeyWordAndContent) {
        this.separatedKeyWordAndContent = separatedKeyWordAndContent;
        doTodoCommand();
    }
    public void doTodoCommand() {
        if (doesIndexOutOfBoundsOccur(separatedKeyWordAndContent,1, EMPTY_TODO_DESCRIPTION)) {
            return;
        }
        try {
            addTodoTask(separatedKeyWordAndContent[1]);
        } catch(EmptyDescription e) {
            System.out.println(EMPTY_TODO_TASK);
        }
    }

    private boolean doesIndexOutOfBoundsOccur(String[] stringArray, int index, String outputMessage) {
        try {
            String test = stringArray[index];
            return false;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println(outputMessage);
            return true;
        }
    }

    private void addTodoTask(String content) throws EmptyDescription {
        if (content.trim().isEmpty()) {
            throw new EmptyDescription();
        }
        Task task = new ToDo(content);
        Common.tasks.add(task);
        System.out.println(ADDING_TASK);
        task.printTask();
        System.out.println("Now you have " + Common.tasks.size() + " tasks in the list.");
        Common.dataFile.appendTaskToDataFile(task.putInputToDataFile());
    }

}
