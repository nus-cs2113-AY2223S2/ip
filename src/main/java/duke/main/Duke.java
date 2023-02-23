package duke.main;


import java.util.ArrayList;
import java.io.IOException;

import duke.Storage.Storage;
import duke.tasks.*;

import static duke.Storage.Storage.*;
import static java.lang.System.exit;

public class Duke {
    static final int limitTask = 100;
    static ArrayList<Task> tasksList = new ArrayList<>();
    public static int taskCount = 0;

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList();
    }

    public void run() throws IOException {
        try {
            storage.initFile();
            storage.loadFile(tasks);
        } catch (IOException e) {
            System.out.println("I/O Error! ");
            exit(1);
        }

        ui.greet();
        ui.run(tasks, storage);
        ui.bye();

    }

    public static void main(String[] args) throws IOException {
        new Duke("./taskslist.csv").run();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
}
