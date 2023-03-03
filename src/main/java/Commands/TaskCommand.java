package Commands;

import java.util.ArrayList;

import Exceptions.InvalidFindStringException;
import Exceptions.InvalidTaskDescription;
import Exceptions.InvalidTaskNumberException;
import Exceptions.MissingDescriptionException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

public class TaskCommand {

    /**
     * Marks the task at taskIndex as done
     * 
     * @param taskList  the ArrayList of the user's tasks
     * @param command   String[] of the user's input
     * @param taskIndex index of the task to be marked as done
     * @throws InvalidTaskNumberException
     */
    public static void markTask(ArrayList<Task> taskList, String[] command, int taskIndex)
            throws InvalidTaskNumberException {
        // mark task as done

        if (taskList == null || taskIndex < 0 || taskIndex >= taskList.size() || taskList.get(taskIndex) == null) {
            throw new InvalidTaskNumberException("Invalid task index");
        }

        PrintCommands.printLine();
        System.out.println("\tNice! I've marked this task as done:");
        taskList.get(taskIndex).markAsDone();
        System.out.println("\t" + taskList.get(taskIndex).getType() + taskList.get(taskIndex).getStatusIcon()
                + taskList.get(taskIndex).description);
        PrintCommands.printLine();
    }

    /**
     * Marks the task at taskIndex as not done
     * 
     * @param taskList  the ArrayList of the user's tasks
     * @param command   String[] of the user's input
     * @param taskIndex index of the task to be marked as not done
     * @throws InvalidTaskNumberException
     */
    public static void unmarkTask(ArrayList<Task> taskList, String[] command, int taskIndex)
            throws InvalidTaskNumberException {

        if (taskList == null || taskIndex < 0 || taskIndex >= taskList.size() || taskList.get(taskIndex) == null) {
            throw new InvalidTaskNumberException("Invalid task index");
        }

        PrintCommands.printLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        taskList.get(taskIndex).markAsNotDone();
        System.out.println(
                "\t" + taskList.get(taskIndex).getType() + taskList.get(taskIndex).getStatusIcon()
                        + taskList.get(taskIndex).description);
        PrintCommands.printLine();
    }

    /**
     * Adds a new task to the ArrayList, of type "todo"
     * 
     * @param taskList the ArrayList of the user's tasks
     * @param command  String[] of the user's input
     * @throws MissingDescriptionException
     * @throws InvalidTaskDescription
     */
    public static void todoTask(ArrayList<Task> taskList, String[] command)
            throws MissingDescriptionException, InvalidTaskDescription {
        if (!isValidTodo(command)) {
            throw new MissingDescriptionException(null);
        }
        String todoDescription = command[1]; // Get the description of the task from the user input
        taskList.add(new Todo(todoDescription)); // Add the new todo task to the arraylist
        PrintCommands.printTodoMessage(taskList);
    }

    /**
     * Adds a new task to the ArrayList, of type "deadline"
     * 
     * @param taskList the ArrayList of the user's tasks
     * @param command  String[] of the user's input
     * @throws MissingDescriptionException
     * @throws InvalidTaskDescription
     */
    public static void deadlineTask(ArrayList<Task> taskList, String[] command)
            throws MissingDescriptionException, InvalidTaskDescription {
        if (!isValidDeadline(command)) {
            throw new MissingDescriptionException(null);
        }
        String[] deadlineCommand = command[1].split(" /by", 2);

        String deadlineDescription = deadlineCommand[0]; // Get description of the user input
        String byDate = deadlineCommand[1]; // Deadline of the user input
        taskList.add(new Deadline(deadlineDescription, byDate)); // Add the new deadline to the arraylist
        PrintCommands.printDeadlineMessage(taskList);
    }

    /**
     * Adds a new task to the ArrayList, of type "event"
     * 
     * @param taskList the ArrayList of the user's tasks
     * @param command  String[] of the user's input
     * @throws MissingDescriptionException
     * @throws InvalidTaskDescription
     */
    public static void eventTask(ArrayList<Task> taskList, String[] command)
            throws MissingDescriptionException, InvalidTaskDescription {
        if (!isValidEvent(command)) {
            throw new MissingDescriptionException(null);
        }
        String[] eventCommand = command[1].split(" /from | /to");

        String eventDescription = eventCommand[0]; // Get event description from user input
        String eventFromDate = eventCommand[1]; // Get event from date
        String eventToDate = eventCommand[2]; // Get event to date
        taskList.add(new Event(eventDescription, eventFromDate, eventToDate)); // Add the new event to the arraylist
        PrintCommands.printEventMessage(taskList);
    }

    /**
     * Retrives the index of the Task from the user input, for various purposes
     * 
     * @param taskList the ArrayList of the user's tasks
     * @param command  String[] of the user's input
     * @return the index of the task for various commands
     * @throws InvalidTaskNumberException
     * @throws NumberFormatException
     */
    public static int getTaskIndex(String[] command, ArrayList<Task> taskList)
            throws InvalidTaskNumberException, NumberFormatException {

        if (command.length < 2) {
            throw new NumberFormatException();
        }
        int TaskIndex = Integer.parseInt(command[1]) - 1; // 0-base

        if (TaskIndex < 0 || TaskIndex > taskList.size()) {
            throw new InvalidTaskNumberException(null);
        } else {
            return TaskIndex;
        }
    }

    /**
     * Checks if a todo command is valid
     * 
     * @param command String[] of the user's input
     * @return true if the command is a valid todo
     */
    public static boolean isValidTodo(String[] command) {
        boolean isValidTodo = true;
        if (command.length < 2) {
            isValidTodo = false;
        }
        return isValidTodo;
    }

    /**
     * Checks if a deadine command is valid, and in the correct format with all
     * required parameters
     * 
     * @param command String[] of the user's input
     * @return true if the command is a valid deadline
     */
    public static boolean isValidDeadline(String[] command) {
        boolean hasDate = false;

        for (String s : command) {
            if (s.contains("/by")) {
                hasDate = true;
            }
        }
        return hasDate;
    }

    /**
     * Checks if an event command is valid, and in the correct format with all
     * required parameters
     * 
     * @param command String[] of the user's input
     * @return true if the command is a valid event
     */
    public static boolean isValidEvent(String[] command) {
        boolean hasFromDate = false;
        boolean hasToDate = false;

        for (String s : command) {
            if (s.contains("/from")) {
                hasFromDate = true;
            }
            if (s.contains("/to")) {
                hasToDate = true;
            }
        }
        return hasFromDate && hasToDate;
    }

    /**
     * Deletes the task at the taskIndex index from the ArrayList taskList
     * 
     * @param taskList the ArrayList of the user's tasks
     * @param command String[] of the user's input
     * @param taskIndex index of the task to be deleted
     * @throws InvalidTaskNumberException
     */
    public static void deleteTask(ArrayList<Task> taskList, String[] command, int taskIndex)
            throws InvalidTaskNumberException {

        if (taskList == null || taskIndex < 0 || taskIndex >= taskList.size() || taskList.get(taskIndex) == null) {
            throw new InvalidTaskNumberException("Invalid task index");
        }
        PrintCommands.printDeleteMessage(taskList, taskIndex);
        PrintCommands.printLine();
        taskList.remove(taskIndex); // delete the specified task number
    }

    /**
     * Finds the tasks that the user specified
     * 
     * @param taskList the ArrayList of the user's tasks
     * @param command String[] of the user's input
     * @throws InvalidFindStringException
     */
    public static void findTask(ArrayList<Task> taskList, String[] command) throws InvalidFindStringException{
        try {
        PrintCommands.printFindMessage(taskList, command);
        } catch (InvalidFindStringException ifne) {
            //System.out.println("Uh oh! The task you are looking for does not exist, or there were some issues. Please try again.");
        }
    }
}
