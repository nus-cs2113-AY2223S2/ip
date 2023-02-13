package AllCommands;

import Support.TaskList;

public class FindCommand {
    public static String line;
    public FindCommand(String line) {
        FindCommand.line = line;
    }

    public static void findCommandMethod(TaskList tasks) {
        System.out.println("____________________________________________________________");
        String findTask = line.substring(5);
        boolean ifFindTask = false;
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDescription().contains(findTask)) {
                System.out.print(i + 1);
                System.out.println(". " + tasks.getTask(i).showTask());
                ifFindTask = true;
            }
        }
        if (!ifFindTask) {
            System.out.println("Oops! We can not find your task, please try again.");
        }
        System.out.println("____________________________________________________________");
    }
}
