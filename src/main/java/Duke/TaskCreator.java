package Duke;
/**
 * Responsible for allocating the task creation to according task subclasses.
 * Can create tasks from commands input by user, as well as tasks stored in the text file.
 */

import java.util.Arrays;

public class TaskCreator {
    /**
     * Creates tasks from command entered by user.
     *
     * @param commandByWord Contains information on task.
     * @return New task.
     * @throws IllegalArgumentException When insufficient information is provided.
     * @throws ArrayIndexOutOfBoundsException When insufficient information is provided.
     */
    public static Task createNewTask(String[] commandByWord)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        String type = commandByWord[0];
        if (type.equals("todo")) {
            return ToDo.createToDo(commandByWord);
        } else if (type.equals("deadline")) {
            return Deadline.createDeadline(commandByWord);
        } else {
            return Event.createEvent(commandByWord);
        }
    }

    /**
     * Creates tasks from task saved in text file.
     *
     * @param commandByWord Contains information on task.
     * @return New task.
     * @throws IllegalArgumentException When insufficient information is provided.
     * @throws ArrayIndexOutOfBoundsException When insufficient information is provided.
     */
    public static Task createSavedTask(String[] commandByWord)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        Task task = createNewTask(Arrays.copyOf(commandByWord, commandByWord.length - 2));
        if (commandByWord[commandByWord.length - 1].equals("done")) task.markAsComplete();
        return task;
    }
}
