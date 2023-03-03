import java.io.IOException;

/**
 * This is the main file of the chatbot, Duke.
 * Duke enables users to keep track of tasks
 * that require to be completed ranging from
 * to do, events and deadline.
 *
 * It allows users to have a saved state of
 * the list everytime they open Duke, and
 * gives them the freedom to mark/unmark
 * tasks as they go along their day.
 */
public class Duke {
    protected static Storage storage;
    protected static Parser parser;
    protected static Ui ui;

    /**
     * Creates new objects for UI, Storage, Parser
     */
    public Duke() {
        storage = new Storage();
        parser = new Parser();
        ui = new Ui();
    }

    /**
     * @param args User input. It could be to add
     *             to do, events deadline, list, delete
     *
     * @throws IOException if file does not exist
     */
    public static void main(String[] args) throws IOException {
        Ui.printWelcome();
        Storage.fileExists();
        Parser.cmdToExcecute();
        Storage.saveData();
        Ui.printExit();
    }
}