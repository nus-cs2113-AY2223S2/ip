package duke.main;

import duke.Storage.Storage;
import duke.tasks.TaskList;

import java.io.IOException;
import java.util.Scanner;

import duke.command.Parser;

import static duke.main.Duke.printHorizontalLine;

/**
 * Main Ui for Duke App
 */
public class Ui {
    /**
     * greetings when app is started
     */
    public void greet() {
        printHorizontalLine();
        System.out.println("Hello! I'm duke.main.Duke\n" + " What can I do for you?\n");
        printHorizontalLine();
    }

    /**
     * Ending message when app is closing
     */
    public void bye() {
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * Start the main ui
     *
     * @param tasks   list of tasks
     * @param storage storage object handle action related to file operation
     * @throws IOException
     */

    public void run(TaskList tasks, Storage storage) throws IOException {
        Scanner in = new Scanner((System.in));
        Parser parser = new Parser();

        String line;

        while (true) {
            System.out.println("Enter your command here: ");
            line = in.nextLine().trim();
            parser.processLine(line, tasks, storage);
            if (parser.isExit()) {
                break;
            }
        }
    }
}

