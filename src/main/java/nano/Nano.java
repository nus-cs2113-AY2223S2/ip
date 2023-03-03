package nano;

import nano.data.TaskList;
import nano.data.exception.NanoCommandException;
import nano.parser.Parser;
import nano.storage.Storage;
import nano.ui.Ui;

import java.io.IOException;

public class Nano {

    private static Storage storage;
    private static Ui ui;
    private static TaskList tasks;

    public Nano(String filePath) {
        //chatbot startup
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    public void run() {
        while (true) {
            String userInput = ui.getUserInput();
            try {
                Parser.executeCommand(userInput, tasks);
                storage.saveTaskFile(tasks.getTaskList());
            } catch (NanoCommandException commandException) {
                Ui.displayUnknownCommandMessage();
            } catch (IOException e) {
                System.out.println("Error saving file.");
            }
        }
    }

    public static void main(String[] args) {
        new Nano("data/tasks.txt").run();
    }
}