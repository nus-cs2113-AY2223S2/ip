package duke;

import duke.exceptions.FormatException;
import duke.exceptions.NoDescriptionException;

import java.util.ArrayList;

/**
 * The TaskList class deals with modifying the task list, including add task or deleting task.
 */
public class TaskList {

    /**
     * Add a new task to the list.
     *
     * @param tasks The list containing the user's tasks.
     * @param t The task that the user want to add
     */
    public static void addTask(ArrayList<Task> tasks, Task t) {
        tasks.add(t);
    }

    /**
     * Delete the task of the index entered by the user from the list.
     *
     * @param tasks The list containing the user's tasks.
     * @param commandArgs The index description entered by the user.
     * @throws NoDescriptionException If the index description is empty, the exception will be thrown.
     * @throws IndexOutOfBoundsException If the index is not within the size of the list, the exception will be thrown.
     * @throws FormatException If the index is not a Integer, the exception will be thrown.
     */
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
        Ui.printLine();
        tasks.remove(deleteId);
    }
}
