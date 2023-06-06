import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Represents a Personal Assistant Chat bot that helps a person to keep track of various things.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;
    private final String FILE_PATH = "data/duke.txt";
    private final String DIR_PATH = "data";
    private final String BYE = "bye";

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Initializes the task list to store tasks, ui for interaction with user,
     * and storage to store information in duke.txt file.
     */
    public Duke() {

        ui = new Ui();
        storage = new Storage(DIR_PATH, FILE_PATH);

        try {
            taskList = storage.load(ui);
            ui.showLoadingSuccess();
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Represents the main run time function interacting with user.
     * Sends input from user to the respective classes for execution.
     */
    public void run() {

        ui.printLogo();
        String fullCommand = ui.readInput();

        while (!fullCommand.equals(BYE)) {
            ui.printLine();

            Command c = Parser.parseInput(fullCommand);
            c.execute(taskList, ui, storage);

            ui.printLine();
            fullCommand = ui.readInput();
        }

        storage.save(taskList, ui);
        ui.showGoodbye();
    }

}
