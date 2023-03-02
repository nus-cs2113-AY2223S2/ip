
import java.io.FileNotFoundException;
import java.io.IOException;

import psyduck.command.*;
import psyduck.ui.*;
import psyduck.tasklist.TaskList;
import psyduck.storage.Storage;

public class Psyduck {
    private static Storage storage;

    private final String filepath = "save.txt";
    private final Ui ui;
    private final TaskList tasks = new TaskList();
    private static boolean shouldExit = false;

    public void setShouldExit(boolean shouldExit) {
        Psyduck.shouldExit = shouldExit;
    }

    /**
     * Initialises the program by loading in the save file.
     *
     * @param filepath the path containing the save file.
     */
    public Psyduck(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            Storage.readFile(filepath, tasks);
        } catch (FileNotFoundException e) {
            ErrorMessage.printFileNotFoundMessage();
        }
    }

    /**
     * Runs the main program of Psyduck.
     */
    public void run() {
        ui.greet();
        do {
            String input = ui.readInput();
            CommandHandler handler = new CommandHandler();
            handler.processCommands(input, tasks, ui);
            shouldExit = handler.canExit;
        } while (!shouldExit);
        ui.sayBye();
        try {
            Storage.writeToFile(filepath, tasks);
        } catch (IOException e) {
            System.out.println("Error occurred when saving file. Aborting.");
        }
    }

    public static void main(String[] args) {
        new Psyduck("save.txt").run();
    }
}
