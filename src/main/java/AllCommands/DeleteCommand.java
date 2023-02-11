package AllCommands;

import Support.TaskList;

public class DeleteCommand {
    public static String line;
    public DeleteCommand(String line) {
        DeleteCommand.line = line;
    }

    public static void deleteCommandMethod(TaskList tasks) {
        System.out.println("____________________________________________________________");
        int deleteIndex = Integer.parseInt(line.split(" ")[1]) - 1;
        if (deleteIndex > tasks.getSize() - 1) {
            System.out.println("Oops, you can not delete the task that is outside the boundary.");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + tasks.getTask(deleteIndex).showTask());
            tasks.deleteTask(deleteIndex);
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        }
        System.out.println("____________________________________________________________");
    }
}