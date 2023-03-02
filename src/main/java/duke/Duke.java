package duke;

import java.util.Scanner;
import java.io.IOException;

/**
 * Primary class representing an instance of a Duke Chatbot.
 */
public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    /**
     * Initializes other classes for use in the program. Reads tasks stored on memory from previous sessions.
     *
     * @param filePath Name of filepath where task data is stored.
     */
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

    /** 
     * Takes input from standard input. Passes input into Parser to be processed.
     * Finally, calls writeToFile method to store results on memory.
     */
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

    
    /** 
     * Starts execution of program by creating an instance of the Duke class.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Duke("duke.txt").run();
    }
}
