package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

import java.util.Scanner;

/**
 * Main class to manage all operations
 */
public class Duke {

    private static TaskList tasks;

    /**
     * Initialise the storage, load the data from the text file and executes the program
     */
    public Duke(){
        tasks = new TaskList(Storage.storageInit());
        String input;
        Scanner in = new Scanner(System.in);
        UI.showWelcomeMessage();
        do {
            input = in.nextLine();
            if (DukeException.hasError(input)) {
                continue;
            }
            run(input);
        } while (!input.equals("bye"));
    }
    private static void run(String input) {
        Command command = Parser.parse(input);
        command.execute(tasks);
    }
    /**
     * Command to start the program
     */
    public static void main(String[] args) {
        new Duke();
    }
}
