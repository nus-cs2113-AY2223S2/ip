package duke;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        storage.initialise();
    }

    public void run() {
        while (true) {
            String[] inputLine = ui.getCommand().split(" ", 2);
            String command = inputLine[0];
            tasks.executeCommand(inputLine, command, storage);
        }
    }

    public static void main(String[] args) {
        new Duke("data/duketasks.txt").run();
    }

}

