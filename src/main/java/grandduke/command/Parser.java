package grandduke.command;

import grandduke.task.TaskList;
import grandduke.task.Deadline;
import grandduke.task.Event;
import grandduke.task.Task;
import grandduke.task.Todo;
import grandduke.exception.GrandException;
import grandduke.exception.UnrecognisedCommandException;
import grandduke.exception.deadline.EmptyDeadlineDateException;
import grandduke.exception.deadline.EmptyDeadlineDescriptionException;
import grandduke.exception.deadline.EmptyDeadlineException;
import grandduke.exception.deadline.MissingByException;
import grandduke.exception.event.EmptyEventDescriptionException;
import grandduke.exception.event.EmptyEventException;
import grandduke.exception.event.EmptyEventFromException;
import grandduke.exception.event.EmptyEventToException;
import grandduke.exception.event.MissingFromException;
import grandduke.exception.event.MissingToException;
import grandduke.exception.todo.EmptyTodoException;

public abstract class Parser {
    /**
     * Parses the command from the user and decides on the appropriate command to
     * execute
     * @param input the command sent by the user
     */
    public static void parseCommand(String input) throws UnrecognisedCommandException {
        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];

        String commandDetails;

        if (inputArr.length > 1) {
            commandDetails = inputArr[1].strip();
        } else {
            commandDetails = Io.EMPTY_COMMAND;
        }

        try {
            switch (command) {
            case Io.LIST_COMMAND:
                TaskList.printTaskList();
                break;

            case Io.MARK_COMMAND:
                TaskList.markTask(commandDetails, true);
                break;

            case Io.UNMARK_COMMAND:
                TaskList.markTask(commandDetails, false);
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

            case Io.DELETE_COMMAND:
                TaskList.deleteTask(commandDetails);
                break;

            case Io.FIND_COMMAND:
                TaskList.findTasks(commandDetails);
                break;

            default:
                throw new UnrecognisedCommandException();
            }
        } catch (GrandException e) {
            Io.printOutput(e.getMessage());
        }
    }

    /**
     * Parses the input and creates a new task based on the type of task
     * @param input the input from the user
     * @param type the type of task
     * @return the new task
     */
    public static Task parseNewTask(String input, String type) throws GrandException {
        Task newTask;

        try {
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
        } catch (GrandException e) {
            throw e;
        }

        return newTask;
    }

    /**
     * Creates a new Todo task
     * @param input the input from the user
     * @return the new Todo task
     */
    public static Task createTodo(String input) throws EmptyTodoException {
        if (input.equals(Io.EMPTY_COMMAND)) {
            throw new EmptyTodoException();
        }
        return new Todo(input);
    }

    /**
     * Creates a new Deadline task
     * @param input the input from the user
     * @return the new Deadline task
     */
    public static Task createDeadline(String input) throws EmptyDeadlineException, MissingByException,
            EmptyDeadlineDescriptionException, EmptyDeadlineDateException {

        if (input.equals(Io.EMPTY_COMMAND)) {
            throw new EmptyDeadlineException();
        }

        if (!input.contains("/by")) {
            throw new MissingByException();
        }

        String[] inputList = input.split("/by", 2);
        String taskDesc = inputList[0].strip();
        if (taskDesc.equals(Io.EMPTY_COMMAND)) {
            throw new EmptyDeadlineDescriptionException();
        }

        String deadline = inputList[1].strip();
        if (deadline.equals(Io.EMPTY_COMMAND)) {
            throw new EmptyDeadlineDateException();
        }

        return new Deadline(taskDesc, deadline);
    }

    /**
     * Creates a new Event task
     * @param input the input from the user
     * @return the new Event task
     */
    public static Task createEvent(String input) throws EmptyEventException, MissingFromException,
            MissingToException, EmptyEventDescriptionException, EmptyEventFromException,
            EmptyEventToException {

        if (input.equals(Io.EMPTY_COMMAND)) {
            throw new EmptyEventException();
        }

        if (!input.contains("/from")) {
            throw new MissingFromException();
        }

        if (!input.contains("/to")) {
            throw new MissingToException();
        }

        String[] inputList = input.split("/from", 2);
        String[] inputListFromTo = inputList[1].split("/to", 2);
        String taskDesc = inputList[0].strip();

        if (taskDesc.equals(Io.EMPTY_COMMAND)) {
            throw new EmptyEventDescriptionException();
        }

        String eventFrom = inputListFromTo[0].strip();
        if (eventFrom.equals(Io.EMPTY_COMMAND)) {
            throw new EmptyEventFromException();
        }

        String eventTo = inputListFromTo[1].strip();
        if (eventTo.equals(Io.EMPTY_COMMAND)) {
            throw new EmptyEventToException();
        }

        return new Event(taskDesc, eventFrom, eventTo);
    }
}
