// Java program to read data of various types using Scanner class.
package duke;

/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 *
 * This is the main Java driver and has most of the important methods
 *
 **/
public class Duke {
    private final Storage storage;
    private final Tasks tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);

        try {
            tasks = new Tasks(storage.readFile());
        } catch (DukeException e) {
            Ui.printErrorMessage(e.getErrorMessage());
            tasks = new Tasks();
        }
    }
    public void run() {
        Ui.printWelcomeMessage();
        String inputCommand = "";
        boolean isBye = false;

        do {
            inputCommand = Ui.getCommand();
            Ui.printLine();
            try {
                Command command = parser.parseCommand(inputCommand);
                isBye = command.execute();
                storage.saveFile(tasks);
            } catch (DukeException e) {
                Ui.printErrorMessage(e.getErrorMessage());
            }

        } while(!isBye);

        Ui.printByeMessage();
    }
    public static void main(String[] args) {
        new Duke("data/input.txt").run();
    }
}
