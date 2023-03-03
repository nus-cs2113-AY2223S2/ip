package duke;

import duke.Commands.Command;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import dukeException.DukeException;

/**
 * Duke is a personal assistant chatbot that helps a user keep track of various tasks.
 * The user can input tasks such as todo, deadline, and event, and Duke will manage them by adding, deleting, or marking
 * them as done. Duke can also list all the tasks and find tasks that match certain keywords. Duke is able to save the
 * tasks in a file and load them when the user runs the program again.
 */
public class Duke extends Command {

    /**
     * A constant string that represents the line spacing used in the output.
     */
    public static final String LINE_SPACING = "\t____________________________________________________________";

    private Storage storage;
    private final Ui ui;

    /**
     * Creates a Duke object and initializes it with a Ui object and a Storage object.
     * The storage object is created using the specified file path.
     *
     * @param filePath The file path of the file that stores the task list.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(tasks);
    }

    /**
     * The main run loop of Duke. Displays a greeting message to the user and repeatedly prompts for user input,
     * parsing the input into a command and executing it. The loop continues until the user types the "bye" command.
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.cmd();
                isExit = c.getExit();
            } catch (DukeException e) {
                System.out.println("error interrpetting input");
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The entry point of the Duke application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
