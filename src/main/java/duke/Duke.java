package duke;

import duke.exceptions.InvalidCommandException;

import java.io.File;
import java.io.IOException;

public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;
    public static final String FILEPATH = "./data/savedlist.txt";

    private static void parseResponse(String response, TaskList list) {
        if (response.equals("list")) {
            ui.printSeparator();
            ui.displayList(taskList.getTasks());
            ui.printSeparator();
        } else if (response.length() >= 5 && response.substring(0, 5).equals("mark ")) {
            ui.printSeparator();
            list.markTask(response.substring(5));
            ui.printSeparator();
        } else if (response.length() >= 7 && response.substring(0, 7).equals("unmark ")) {
            ui.printSeparator();
            list.unmarkTask(response.substring(7));
            ui.printSeparator();
        } else if (response.length() >= 7 && response.substring(0, 7).equals("delete ")) {
            ui.printSeparator();
            list.deleteTask(response.substring(7));
            ui.printSeparator();
        } else {
            ui.printSeparator();
            try {
                list.listAdd(response);
            } catch (InvalidCommandException e) {
                ui.printInvalidCommandException();
            }
            ui.printSeparator();
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            taskList = storage.loadTaskList();
            ui.printValidSave();
        } catch (IOException ex) {
            ui.printNoValidSave();
            taskList = new TaskList();

        } catch (ClassNotFoundException ex) {
            ui.printClassNotFoundException();
        }
    }

    public void run() {
        ui.greet();

        String userInput = ui.getInput();
        while (!userInput.equals("bye")) {
            parseResponse(userInput, taskList);
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
            ui.printIOException();
        }
        ui.farewell();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
