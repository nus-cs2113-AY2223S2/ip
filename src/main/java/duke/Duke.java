package duke;

import duke.commands.Command;
import duke.commands.Parser;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * The main class. Reads in different commands from the user, and parses them accordingly.
 */
public class Duke {

    public static TaskList taskList = new TaskList();

    /**
     * Main method called when the program is running.
     *
     * @param args Not used, should be empty.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Ui.printGreetings();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = in.nextLine();
            Command command = Parser.handleUserInputs(userInput, taskList);
            if (command == null) {
                Ui.printLine();
                continue;
            }
            command.execute(taskList);
            Ui.printLine();
            isRunning = !command.isExit();
        }
    }
}
