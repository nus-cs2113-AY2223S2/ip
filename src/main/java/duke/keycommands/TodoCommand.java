package duke.keycommands;

import duke.Common;
import duke.Task;
import duke.exception.EmptyDescription;

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
        System.out.println("[T][] " + content);
        System.out.println("Now you have " + Common.tasks.size() + " tasks in the list.");
        String inputToDataFile = "T | " + convertMarkingStatusToNumber(task) + " | " + task.getContent() + "\n";
        Common.dataFile.appendTaskToDataFile(inputToDataFile);
    }

    private String convertMarkingStatusToNumber(Task task) {
        if (task.getMarkingStatus().equals("[ ]")) {
            return "0";
        } else {
            return "1";
        }
    }
}
