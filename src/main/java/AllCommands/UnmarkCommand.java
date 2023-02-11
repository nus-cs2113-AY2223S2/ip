package AllCommands;

import Support.TaskList;

public class UnmarkCommand {
    public static String line;
    public UnmarkCommand(String line) {
        UnmarkCommand.line = line;
    }

    public static void unmarkCommandMethod(TaskList tasks) {
        int taskNumber = Integer.parseInt(line.substring(7));
        tasks.getTask(taskNumber - 1).setIsDone(false);
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.getTask(taskNumber - 1).showTask());
        System.out.println("____________________________________________________________");
    }

}