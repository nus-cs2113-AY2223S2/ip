package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class to manage all operations
 */
public class Duke {

    private static TaskList tasks;

    /**
     * Loads the text file and executes the program
     * @throws FileNotFoundException if error occurs during the checking of file access
     * @throws IOException if error occurs during the file load
     */
    public Duke(){
        try {
            Storage.checkFileAccess();
            tasks = new TaskList(Storage.load());
        } catch (FileNotFoundException err) {
            System.out.println("File not Found");
        } catch (IOException err) {
            System.out.println("Something went wrong: " + err.getMessage());
        }
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
