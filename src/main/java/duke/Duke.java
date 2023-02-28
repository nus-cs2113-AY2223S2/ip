package duke;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;

import commands.Command;
import common.Messages;
import exceptions.CreateDirectoryException;
import parser.Parser;
import task.Task;
import task.TaskParser;
import storage.Storage;
import ui.TextUi;

public class Duke {
    private Storage storage;
    private TaskParser taskParser;
    private TextUi ui;
    private static final Path DATA_DIRECTORY = Path.of(System.getProperty("user.dir"), "data");

    /**
     * Main method to invoke the run method in the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Run the program with the 3 methods listed.
     */
    public void run() {
        start();
        runCommandLoopUntilByeCommand();
        exit();
    }

    /**
     * Start of the program.
     * It will create a Ui instance.
     * It will check if the directory can be created. It creates the directory if it does not exist.
     * It will read and load from a file if it exists. Else it will create the file when a new task is added.
     * It will print the Ui Banner.
     */
    private void start() {
        this.ui = new TextUi();
        this.storage = new Storage(DATA_DIRECTORY);
        try {
            storage.createDirectory();
            taskParser = storage.readAndLoadFromFile();
        } catch (CreateDirectoryException e) {
            System.out.println(String.format(Messages.ERROR_CREATE_DIRECTORY, e));
        } catch (FileNotFoundException e) {
            taskParser = new TaskParser(new ArrayList<Task>());
            System.out.println(Messages.ERROR_STORAGE_FILE_NOT_FOUND);
        }
        ui.printBanner();
    }

    /**
     * The program will keep running until the user types the "bye" command.
     */
    private void runCommandLoopUntilByeCommand() {
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.getUserInput();
            Command command = new Parser().parseCommand(userInput);
            command.execute(taskParser, ui, storage);
            isExit = command.isExit();
        }
        ui.printMessage(Messages.MESSAGE_COMMAND_BYE);

    }

    /**
     * Exits the program.
     */
    private void exit() {
        System.exit(0);
    }
}
