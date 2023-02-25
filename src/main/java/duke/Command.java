package duke;

import duke.exceptions.FormatException;
import duke.exceptions.NoDescriptionException;

import java.util.ArrayList;

public class Command {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void addTodo(ArrayList<Task> tasks, String commandArgs) throws NoDescriptionException {
        final String taskName = commandArgs.trim();
        if (taskName.length() == 0) {
            throw new NoDescriptionException();
        }
        TaskList.addTask(tasks, new Todo(commandArgs));
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
        TaskList.addTask(tasks, new Event(eventDescription, from, to));
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
        TaskList.addTask(tasks, new Deadline(deadlineDescription, deadline));
    }
    public static void printListOfTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(tasks.get(i));
        }
        printLine();
    }
    public static void find(ArrayList<Task> tasks, String keyword) {
        for (int i = 0; i < tasks.size(); i += 1) {
            if (tasks.get(i).description.contains(keyword)) {
                System.out.println(tasks.get(i).toString());
            }
        }
        printLine();
    }
    public static void unmarkTask(ArrayList<Task> tasks, String commandArgs) throws NoDescriptionException, IndexOutOfBoundsException {
        if (commandArgs.trim().length() == 0) {
            throw new NoDescriptionException();
        }
        final int unmarkId = Integer.parseInt(commandArgs) - 1;
        if (unmarkId < 0 || unmarkId >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (!tasks.get(unmarkId).isDone) {
            System.out.println("This task hasn't been marked as done yet ∪･ω･∪");
        } else {
            tasks.get(unmarkId).markAsNotDone();
            System.out.println("I've unmarked this task ∪･ω･∪:");
            System.out.println(tasks.get(unmarkId));
        }
        printLine();
    }
    public static void markTask(ArrayList<Task> tasks, String commandArgs) throws NoDescriptionException, IndexOutOfBoundsException {
        if (commandArgs.trim().length() == 0) {
            throw new NoDescriptionException();
        }
        final int markId = Integer.parseInt(commandArgs) - 1;
        if (markId < 0 || markId >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (tasks.get(markId).isDone) {
            System.out.println("This task has already been marked as done ੯•໒꒱❤︎");
        } else {
            tasks.get(markId).markAsDone();
            System.out.println("I've marked this task as done ੯•໒꒱❤︎:");
            System.out.println(tasks.get(markId));
        }
        printLine();
    }
}
