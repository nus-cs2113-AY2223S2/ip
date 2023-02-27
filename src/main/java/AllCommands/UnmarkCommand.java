package AllCommands;

import BasisSupport.Spliterator;
import Support.TaskList;

public class UnmarkCommand {
    public static String line;
    public UnmarkCommand(String line) {
        UnmarkCommand.line = line;
    }

    /**
     * This method is to deal with the user's request to ummark some command they have input in the storage.
     * User's input will include the position of task they want to mark.
     * This method is always used with the MarkCommand method together.
     * Necessary feedback from the system is done by the println functions.
     * If the information is not found, system will also give correct feedback.
     * It also deals with the case that the input size exceeds the boundary of task list.
     *
     * @param tasks All the tasks we have in the storage
     */
    public static void unmarkCommandMethod(TaskList tasks) {
        int taskNumber = Integer.parseInt(line.substring(7));
        if (taskNumber > tasks.getSize() || taskNumber <= 0) {
            System.out.println("Oops, you can not mark the task that is outside the boundary.");
        } else {
            tasks.getTask(taskNumber - 1).setIsDone(false);
            Spliterator.printSpliterator();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.getTask(taskNumber - 1).showTask());
        }
        Spliterator.printSpliterator();
    }

}