package duke.commands;


import java.io.IOException;
import java.io.File;
import duke.ui.TextUi;
import duke.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;

/**
 *  Entry point of the Duke application.
 *  * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private TextUi ui;
    private TaskList taskList;

    /**
     * Runs the program until termination.
     */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, prints the welcome message.
     * Check whether there's existing history, if yes, load the file.
     */
    private void start() {
        this.ui = new TextUi();
        ui.showWelcomeMessage();

        File duke = new File("./duke.txt");
        if (duke.exists()) {
            System.out.println("Task list already exists.");
            new Storage("./duke.txt").FileReading(taskList);
            new ListCommand().execute();
        } else {
            System.out.println("Your new task list is created!");
        }

    }

    /**
     * Prints the Goodbye message and exits.
     * Stores the task list.
     */
    private void exit() {
        new Storage("./duke.txt").saveFile(taskList);
        System.exit(0);
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            executeCommand(command);
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Executes the command and returns the result.
     * @param command user input command
     */
    private void executeCommand(Command command) {
        try {
            command.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

}
