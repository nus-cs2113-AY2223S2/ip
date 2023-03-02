package duke;

import java.io.IOException;

public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;
    private static Parser parser;
    public static final String FILEPATH = "./savedlist.txt";

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        parser = new Parser();
        try {
            taskList = storage.loadTaskList();
            ui.printValidSave();
        } catch (IOException ex) {
            ui.printNoValidSave();
            taskList = new TaskList();

        } catch (ClassNotFoundException ex) {
            ui.printNoValidSaveClassNotFound();
            taskList = new TaskList();
        }
    }

    private void run() {
        ui.greet();

        String userInput = ui.getInput();
        while (!userInput.equalsIgnoreCase("bye")) {
            parser.parseResponse(userInput, taskList);
            userInput = ui.getInput();
        }

        handleExit();
    }

    private void handleExit() {
        try {
            storage.saveTaskList(taskList);
            ui.printSeparator();
            ui.printSuccessfulSave(FILEPATH);
        } catch (IOException e) {
            ui.printFailedToSave();
        }
        ui.farewell();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
