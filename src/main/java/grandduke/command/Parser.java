package grandduke.command;

import grandduke.task.TaskList;
import grandduke.task.Deadline;
import grandduke.task.Event;
import grandduke.task.Task;
import grandduke.task.Todo;
import grandduke.command.Io;

public abstract class Parser {
    /**
     * Parses the command from the user and decides on the appropriate command to
     * execute
     * 
     * @param input
     *            the command sent by the user
     */
    public static void parseCommand(String input) {
        input = input.toLowerCase();
        String[] inputArr = input.split(" ", 2);

        if (input.equals(Io.LIST_COMMAND)) {
            TaskList.printTaskList();
        } else if (input.startsWith(Io.MARK_COMMAND)) {
            TaskList.markTask(input);
        } else if (input.startsWith(Io.UNMARK_COMMAND)) {
            TaskList.unmarkTask(input);
        } else if (input.startsWith(Io.TODO_COMMAND)) {
            TaskList.addTask(inputArr[1], Io.TODO_COMMAND);
        } else if (input.startsWith(Io.DEADLINE_COMMAND)) {
            TaskList.addTask(inputArr[1], Io.DEADLINE_COMMAND);
        } else if (input.startsWith(Io.EVENT_COMMAND)) {
            TaskList.addTask(inputArr[1], Io.EVENT_COMMAND);
        } else {
            Io.printOutput("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static Task parseNewTask(String input, String type) {
        Task newTask;

        switch (type) {
        case Io.TODO_COMMAND:
            newTask = createTodo(input);
            break;
        case Io.DEADLINE_COMMAND:
            newTask = createDeadline(input);
            break;
        case Io.EVENT_COMMAND:
            newTask = createEvent(input);
            break;
        default:
            newTask = null;
            break;

        }

        return newTask;
    }

    public static Task createTodo(String input) {
        return new Todo(input);
    }

    public static Task createDeadline(String input) {
        String[] inputList = input.split(" /by ");
        String taskDesc = inputList[0];
        String deadline = inputList[1];
        return new Deadline(taskDesc, deadline);
    }

    public static Task createEvent(String input) {
        String[] inputList = input.split(" /from ");
        String[] inputList2 = inputList[1].split(" /to ");
        String taskDesc = inputList[0];
        String eventFrom = inputList2[0];
        String eventTo = inputList2[1];
        return new Event(taskDesc, eventFrom, eventTo);
    }
}
