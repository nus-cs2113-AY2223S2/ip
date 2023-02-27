package AllCommands;

import BasisSupport.Spliterator;
import Support.TaskList;

public class MarkCommand {
    public static String line;
    public MarkCommand(String line) {
        MarkCommand.line = line;
    }

    /**
     * This method is to deal with the user's request to mark some command they have input in the storage.
     * User's input will include the position of task they want to mark.
     * This method is always used with the UnmarkCommand method together.
     * Necessary feedback from the system is done by the println functions.
     * If the information is not found, system will also give correct feedback.
     * It also deals with the case that the input size exceeds the boundary of task list.
     *
     * @param tasks All the tasks we have in the storage
     */

    public static void markCommandMethod(TaskList tasks) {
        int taskNumber = Integer.parseInt(line.substring(5));
        System.out.println(taskNumber);
        if (taskNumber > tasks.getSize() || taskNumber <= 0) {
            System.out.println("Oops, you can not mark the task that is outside the boundary.");
        } else {
            tasks.getTask(taskNumber - 1).setIsDone(true);
            Spliterator.printSpliterator();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.getTask(taskNumber - 1).showTask());
        }
        Spliterator.printSpliterator();
    }

}