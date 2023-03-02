package AllCommands;

import BasisSupport.Spliterator;
import Support.TaskList;

public class FindCommand {
    public static String line;
    public FindCommand(String line) {
        FindCommand.line = line;
    }

    /**
     * This method is to deal with the user's request to find the task with some words.
     * User's input will include the specific information included in some tasks.
     * Necessary feedback from the system is done by the println functions.
     * If the information is not found, system will also give correct feedback.
     *
     * @param tasks All the tasks we have in the storage
     */
    public static void findCommandMethod(TaskList tasks) {
        Spliterator.printSpliterator();
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
        // Give the feedback if the task is not found.
        if (!ifFindTask) {
            System.out.println("Oops! We can not find your task, please try again.");
        }
        Spliterator.printSpliterator();
    }
}
