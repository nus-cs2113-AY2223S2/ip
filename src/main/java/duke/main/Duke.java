package duke.main;

import duke.command.Command;
import duke.command.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * The main class. Reads in commands from the terminal, then parses and runs them.
 */
public class Duke {
    /**
     * Main method called when the program is run.
     *
     * @param args Not used, should be empty anyway.
     */
    public static void main(String[] args) {
        TaskList taskList = Storage.readTasksFromFile(Storage.SAVE_PATH);

        // Try-with-resources to automatically close Scanner
        try (Scanner in = new Scanner(System.in)) {
            Ui.printStartMessage();
            boolean isRunning = true;

            while (isRunning) {
                String str = in.nextLine();
                Command command = Parser.parseCommand(str, taskList);
                command.run(taskList);
                isRunning = !command.isExit();
                Storage.saveTasksToFile(Storage.SAVE_PATH, taskList);
            }
        }
    }
}
