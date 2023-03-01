package duke;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs ui, storage and tasks elements and initialises storage.
     */
    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        storage.initialise();
    }

    /**
     * Continuously takes in and executes user commands.
     */
    public void run() {
        while (true) {
            String[] inputLine = ui.getCommand().split(" ", 2);
            String command = inputLine[0];
            tasks.executeCommand(inputLine, command, storage);
        }
    }

    /**
     * Creates a new duke element and execute the run command.
     */
    public static void main(String[] args) {
        new Duke("data/duketasks.txt").run();
    }

}

