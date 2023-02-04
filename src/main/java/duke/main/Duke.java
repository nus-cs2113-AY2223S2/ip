package duke.main;

import java.util.Scanner;

import duke.command.CommandParser;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        Ui.printStartMessage();
        // Try-with-resources to automatically close Scanner
        try (Scanner in = new Scanner(System.in)) {
            boolean isRunning = true;

            while (isRunning) {
                String str = in.nextLine();
                isRunning = CommandParser.parseCommand(str, taskList);
            }
        }
        Ui.printExitMessage();
    }
}
