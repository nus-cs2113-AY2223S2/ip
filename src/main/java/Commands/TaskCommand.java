package Commands;
import java.util.ArrayList;

import Exceptions.InvalidTaskDescription;
import Exceptions.InvalidTaskNumberException;
import Exceptions.MissingDescriptionException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

public class TaskCommand {

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

    public static void todoTask(ArrayList<Task> taskList, String[] command)
            throws MissingDescriptionException, InvalidTaskDescription {
        if (!isValidTodo(command)) {
            throw new MissingDescriptionException(null);
        }
        String todoDescription = command[1]; // Get the description of the task from the user input
        taskList.add(new Todo(todoDescription)); // Add the new todo task to the arraylist
        PrintCommands.printTodoMessage(taskList, todoDescription);
    }

    public static void deadlineTask(ArrayList<Task> taskList, String[] command)
            throws MissingDescriptionException, InvalidTaskDescription {
        if (!isValidDeadline(command)) {
            throw new MissingDescriptionException(null);
        }
        String[] deadlineCommand = command[1].split(" /by", 2);
        
        String deadlineDescription = deadlineCommand[0]; // Get description of the user input
        String byDate = deadlineCommand[1]; // Deadline of the user input
        taskList.add(new Deadline(deadlineDescription, byDate)); // Add the new deadline to the arraylist
        PrintCommands.printDeadlineMessage(taskList, deadlineDescription);
    }

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
        PrintCommands.printEventMessage(taskList, eventDescription);
    }

    public static int getTaskIndex(String[] command, ArrayList<Task> taskList)
            throws InvalidTaskNumberException, NumberFormatException {

        if (command.length < 2) {
            throw new NumberFormatException();
        }
        int TaskIndex = Integer.parseInt(command[1]) - 1; // 0-base

        if (TaskIndex < 0 || TaskIndex > taskList.size()) {
            throw new InvalidTaskNumberException(null);
        } else {
            // System.out.println("Task Index : " + TaskIndex);
            // System.out.println("Counter : " + taskList.size());
            return TaskIndex;
        }
    }

    public static boolean isValidTodo(String[] command) {
        boolean isValidTodo = true;
        if (command.length < 2) {
            isValidTodo = false;
        }
        return isValidTodo;
    }

    public static boolean isValidDeadline(String[] command) {
        boolean hasDate = false;

        for (String s : command) {
            if (s.contains("/by")) {
                hasDate = true;
            }
        }
        return hasDate;
    }

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

    public static void deleteTask(ArrayList<Task> taskList, String[] command, int taskIndex)
            throws InvalidTaskNumberException {

        if (taskList == null || taskIndex < 0 || taskIndex >= taskList.size() || taskList.get(taskIndex) == null) {
            throw new InvalidTaskNumberException("Invalid task index");
        }
        PrintCommands.printDeleteMessage(taskList, taskIndex);
        PrintCommands.printNumberOfTasks(taskList);
        taskList.remove(taskIndex); // delete the specified task number

    }
}
