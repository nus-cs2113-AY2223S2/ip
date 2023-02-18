package luke;

import luke.command.Command;

import java.io.IOException;

public class Luke {
    /** An object to manage the responses of LUKE */
    private Ui ui;

    /** An object used to manage the tasks added by the user */
    private TaskList taskList;

    /** An object to handle the save and loading of data */
    private Storage storage;

    private Parser parser;

    public Luke() {
        initialize();
    }

    /** Initializes all the objects used in LUKE, says "Hi" to the user */
    private void initialize() {
        // Initialization
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
        this.taskList = storage.loadData();

        // Say "Hi" to the user
        ui.sayHi();
    }

    /** Closes the scanner and says "Bye" to the user */
    public void endProgram() {
        ui.closeScanner();
        try {
            storage.saveTaskList(taskList);
        } catch (IOException e) {
            storage.handleSaveError();
        }
        ui.sayBye();
    }

    /** Continuously reads in the user input until the user keys in "bye" */
    private void run() {
        while (true) {
            String fullCommand = ui.readUserInput();
            if (fullCommand.equals("bye")) {
                return;
            }
            Command command = parser.parse(fullCommand, taskList);
            command.execute(taskList, ui, storage);
        }
    }

    public static void main(String[] args) {
        Luke luke = new Luke();
        luke.run();
        luke.endProgram();
    }
}
