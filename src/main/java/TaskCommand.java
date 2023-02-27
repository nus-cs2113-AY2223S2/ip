import Exceptions.InvalidTaskDescription;
import Exceptions.InvalidTaskNumberException;
import Exceptions.MissingDescriptionException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

public class TaskCommand {

    public static void markTask(int counter, Task[] t, String[] command, int TaskIndex)
            throws InvalidTaskNumberException {
        // mark task as done

        if (t == null || TaskIndex < 0 || TaskIndex >= t.length || t[TaskIndex] == null) {
            throw new InvalidTaskNumberException("Invalid task index");
        }

        PrintCommands.printLine();
        System.out.println("\tNice! I've marked this task as done:");
        t[TaskIndex].markAsDone();
        System.out.println("\t" + t[TaskIndex].getType() + t[TaskIndex].getStatusIcon() + t[TaskIndex].description);
        PrintCommands.printLine();
    }

    public static void unmarkTask(int counter, Task[] t, String[] command, int TaskIndex)
            throws InvalidTaskNumberException {

        if (t == null || TaskIndex < 0 || TaskIndex >= t.length || t[TaskIndex] == null) {
            throw new InvalidTaskNumberException("Invalid task index");
        }

        PrintCommands.printLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        t[TaskIndex].markAsNotDone();
        System.out.println(
                "\t" + t[TaskIndex].getType() + t[TaskIndex].getStatusIcon() + t[TaskIndex].description);
        PrintCommands.printLine();
    }

    public static int todoTask(int counter, Task[] t, String[] command) throws MissingDescriptionException, InvalidTaskDescription {
        if (!isValidTodo(command)) {
            throw new MissingDescriptionException(null);
        } 
        String todoDescription = command[1];
        t[counter] = new Todo(todoDescription);
        PrintCommands.printTodoMessage(t[counter], todoDescription, counter + 1);
        counter++;
        return counter;
    }
    public static int deadlineTask(int counter, Task[] t, String[] command) throws MissingDescriptionException, InvalidTaskDescription {
        if (!isValidDeadline(command)) {
            throw new MissingDescriptionException(null);
        } 
        String[] deadlineCommand = command[1].split(" /by", 2);
        String deadlineDescription = deadlineCommand[0];
        String byDate = deadlineCommand[1];
        t[counter] = new Deadline(deadlineDescription, byDate);
        PrintCommands.printDeadlineMessage(t[counter], deadlineDescription, counter + 1);
        counter++;
        return counter;
    }

    public static int eventTask(int counter, Task[] t, String[] command) throws MissingDescriptionException, InvalidTaskDescription {
        if (!isValidEvent(command)) {
            throw new MissingDescriptionException(null);
        } 
        String[] eventCommand = command[1].split(" /from | /to");
        String eventDescription = eventCommand[0];
        String eventFromDate = eventCommand[1];
        String eventToDate = eventCommand[2];
        t[counter] = new Event(eventDescription, eventFromDate, eventToDate);
        PrintCommands.printEventMessage(t[counter], eventDescription, counter + 1);
        counter++;
        return counter;
    }

    public static int getTaskIndex(String[] command, int counter)
            throws InvalidTaskNumberException, NumberFormatException {

        if (command.length < 2) {
            throw new NumberFormatException();
        }
        int TaskIndex = Integer.parseInt(command[1]) - 1; // 0-base

        if (TaskIndex < 0 || TaskIndex > counter) {
            throw new InvalidTaskNumberException(null);
        } else {
            System.out.println("Task Index : " + TaskIndex);
            System.out.println("Counter : " + counter);
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
}
