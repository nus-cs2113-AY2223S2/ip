package duke;

import java.util.Scanner;
import java.io.IOException;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage("duke.txt");
        try {
            tasks = storage.readFromStorage();
        } catch (IOException e) {
            ui.printSimpleMessage("Error reading from saved data.");
            tasks = new TaskList();
        }
        parser = new Parser(tasks);
    }

    public void run () {

        Scanner in = new Scanner(System.in);
        ui.printSimpleMessage("Hello! I'm Duke\nWhat can I do for you?");

        boolean hasNotExited = true;
        while (hasNotExited) {
            System.out.println();
            String input = in.nextLine();
            hasNotExited = parser.parseInput(input);
            try {
                storage.writeToFile(tasks);     
            } catch (IOException e) {
                ui.printSimpleMessage("Error saving data. Task list may not be saved after program exits.");
            }      
        } 
        in.close();
    }

    public static void main(String[] args) throws IOException {
        new Duke("duke.txt").run();
    }
}
