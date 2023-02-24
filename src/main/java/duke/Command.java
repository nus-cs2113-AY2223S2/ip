package duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Command {
    public static void addTodo(ArrayList<Task> tasks, String commandArgs) throws NoDescriptionException {
        if ((commandArgs.trim()).length() == 0) {
            throw new NoDescriptionException();
        }
        addTask(tasks, new Todo(commandArgs));
    }
    public static void addTask(ArrayList<Task> tasks, Task t) {
        tasks.add(t);
    }
    public static void addEvent(ArrayList<Task> tasks, String commandArgs) throws NoDescriptionException, FormatException {
        final int indexOfFrom = commandArgs.indexOf("from:");
        final int indexOfTo = commandArgs.indexOf("to:");
        if (indexOfTo == -1 || indexOfFrom == -1) {
            throw new FormatException();
        }
        String eventDescription = commandArgs.substring(0, indexOfFrom).trim();
        String from = commandArgs.substring(indexOfFrom, indexOfTo).trim().replace("from:", "").trim();
        String to = commandArgs.substring(indexOfTo).trim().replace("to:", "").trim();
        if (eventDescription.trim().length() == 0 || from.length() == 0 || to.length() == 0) {
            throw new NoDescriptionException();
        }
        addTask(tasks, new Event(eventDescription, from, to));
    }
    public static void addDeadline(ArrayList<Task> tasks, String commandArgs) throws NoDescriptionException, FormatException {
        final int indexOfDeadline = commandArgs.indexOf("by:");
        if (indexOfDeadline == -1) {
            throw new FormatException();
        }
        String deadlineDescription = commandArgs.substring(0, indexOfDeadline).trim();
        if (deadlineDescription.trim().length() == 0) {
            throw new NoDescriptionException();
        }
        String deadline = commandArgs.substring(indexOfDeadline).trim().replace("by:", "");
        if (deadline.trim().length() == 0) {
            throw new NoDescriptionException();
        }
        addTask(tasks, new Deadline(deadlineDescription, deadline));
    }
}
