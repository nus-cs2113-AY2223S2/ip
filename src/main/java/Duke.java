import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    public Duke() {
        ui = new Ui();
        storage = new Storage(DIR_PATH, FILE_PATH);
        try {
            taskList = storage.load();
            ui.showLoadingSuccess();
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

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
