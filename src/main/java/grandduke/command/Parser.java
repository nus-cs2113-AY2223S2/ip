package grandduke.command;

import grandduke.task.TaskList;
import grandduke.task.Deadline;
import grandduke.task.Event;
import grandduke.task.Task;
import grandduke.task.Todo;

public abstract class Parser {
    /**
     * Parses the command from the user and decides on the appropriate command to
     * execute
     * 
     * @param input
     *            the command sent by the user
     */
    public static void parseCommand(String input) {
        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];

        String commandDetails;

        if (inputArr.length > 1) {
            commandDetails = inputArr[1];
        } else {
            commandDetails = "";
        }

        switch (command) {
        case Io.LIST_COMMAND:
            TaskList.printTaskList();
            break;

        case Io.MARK_COMMAND:
            TaskList.markTask(input);
            break;

        case Io.UNMARK_COMMAND:
            TaskList.unmarkTask(input);
            break;

        case Io.TODO_COMMAND:
            TaskList.addTask(commandDetails, Io.TODO_COMMAND);
            break;

        case Io.DEADLINE_COMMAND:
            TaskList.addTask(commandDetails, Io.DEADLINE_COMMAND);
            break;

        case Io.EVENT_COMMAND:
            TaskList.addTask(commandDetails, Io.EVENT_COMMAND);
            break;

        default:
            Io.printOutput("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the input and creates a new task based on the type of task
     * 
     * @param input
     *            the input from the user
     * @param type
     *            the type of task
     * @return the new task
     */
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

    /**
     * Creates a new Todo task
     * 
     * @param input
     *            the input from the user
     * @return the new Todo task
     */
    public static Task createTodo(String input) {
        return new Todo(input);
    }

    /**
     * Creates a new Deadline task
     * 
     * @param input
     *            the input from the user
     * @return the new Deadline task
     */
    public static Task createDeadline(String input) {
        String[] inputList = input.split(" /by ", 2);
        String taskDesc = inputList[0];
        String deadline = inputList[1];
        return new Deadline(taskDesc, deadline);
    }

    /**
     * Creates a new Event task
     * 
     * @param input
     *            the input from the user
     * @return the new Event task
     */
    public static Task createEvent(String input) {
        String[] inputList = input.split(" /from ", 2);
        String[] inputList2 = inputList[1].split(" /to ");
        String taskDesc = inputList[0];
        String eventFrom = inputList2[0];
        String eventTo = inputList2[1];
        return new Event(taskDesc, eventFrom, eventTo);
    }
}
