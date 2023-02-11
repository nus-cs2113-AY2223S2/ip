package AllCommands;

import Support.TaskList;

public class MarkCommand {
    public static String line;
    public MarkCommand(String line) {
        MarkCommand.line = line;
    }

    public static void markCommandMethod(TaskList tasks) {
        int taskNumber = Integer.parseInt(line.substring(5));
        System.out.println(taskNumber);
        tasks.getTask(taskNumber - 1).setIsDone(true);
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.getTask(taskNumber - 1).showTask());
        System.out.println("____________________________________________________________");
    }

}