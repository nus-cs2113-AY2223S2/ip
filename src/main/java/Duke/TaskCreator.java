package Duke;

import java.util.Arrays;

public class TaskCreator {
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

    public static Task createSavedTask(String[] commandByWord)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        Task task = createNewTask(Arrays.copyOf(commandByWord, commandByWord.length - 2));
        if (commandByWord[commandByWord.length - 1].equals("done")) task.markAsComplete();
        return task;
    }
}
