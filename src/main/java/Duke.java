import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static boolean isEnd = false;
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public static void main(String[] args) throws IOException {
        new Duke("./taskSave.txt").run();
    }

    public Duke(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);
        tasks = new TaskList();
    }

    public void run() throws IOException {
        try {
            storage.createSaveFile();
        } catch (IOException exception) {
            System.out.println("☹ OOPS!!! An error has occurred while loading the save file");
        }
        storage.loadSaveFile(tasks);
        ui.printHelloStatement();
        while (!isEnd) {
            String input = ui.getInput();
            Parser parser = new Parser(input);
            parser.handleCommand(tasks, ui);
            storage.updateSaveFile(tasks);
        }
    }
}