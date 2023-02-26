package duke;

import duke.exceptions.FormatException;
import duke.exceptions.NoDescriptionException;

import java.util.ArrayList;

public class TaskList {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public TaskList() {
    }
    public static void addTask(ArrayList<Task> tasks, Task t) {
        tasks.add(t);
    }
    public static void deleteTask(ArrayList<Task> tasks, String commandArgs) throws NoDescriptionException, IndexOutOfBoundsException, FormatException {
        if (commandArgs.trim().length() == 0) {
            throw new NoDescriptionException();
        }
        final int deleteId = Parser.parseIndex(commandArgs) - 1;
        if (deleteId < 0 || deleteId >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        System.out.println("I've deleted this task ∪･ω･∪:");
        System.out.println(tasks.get(deleteId));
        printLine();
        tasks.remove(deleteId);
    }
}
