package duke;

import duke.task.TaskList;

public class Ui {

    private static final int BOX_WIDTH = 100;

    /**
     * Prints a simple line of '----' for neater look.
     */
    protected void printSeparator() {
        String separatorLine = "-".repeat(BOX_WIDTH);
        System.out.println(separatorLine);
    }

    /**
     * Formats display message to be left-justified with vertical bars at both ends
     *
     * @param message Message to be display to the user on the terminal
     */
    protected void printMessage(String message) {
        String outputMessage = String.format("| %-97s|", message);
        System.out.println(outputMessage);
    }

    /**
     * Prints out task-list onto the terminal.
     *
     * @param tasks Task-list to be printed.
     */
    protected void printLoadedTaskList(TaskList tasks) {
        printMessage("Done loading previous task list:");
        String outputMessage = String.format("Loaded a total of %d tasks!", tasks.size());
        printMessage(outputMessage);
    }

    protected void printTaskList(TaskList tasks) {
        int numItems = tasks.size();
        if (numItems == 0) {
            printMessage("List is empty!");
        } else {
            printMessage("Here are the tasks in your list:");
            for (int i = 0; i < numItems; i++) {
                String item = tasks.get(i).toString();
                String outputMessage = String.format("%d.%s", i + 1, item);
                printMessage(outputMessage);
            }
        }
    }
}
