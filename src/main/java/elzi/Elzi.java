// Java program to read data of various types using Scanner class.
package elzi;

import elzi.command.Command;

/**
 * @author : Steven A. O. Waskito
 *
 *      This is the main Java driver code
 *
 **/
public class Elzi extends Throwable {
    private Storage storage;
    private TaskList taskList;
    /**
     * Constructor to initialize Elzi program
     * @param filePath path to store storage.txt
     */
    public Elzi(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList();
        try {
            taskList = new TaskList(storage.readFile());
        } catch (ElziException e) {
            Ui.printErrorMessage("filepath error");
            taskList = new TaskList();
        }
    }
    /**
     * Method to run the program, inquire command, print response
     */
    public void run() {
        Ui.printWelcomeMessage();
        String inputCommand = "";
        boolean isBye = false;

        do {
            inputCommand = Ui.getCommand();
            Ui.printLine();
            try {
                Command command = Parser.parse(inputCommand);
                if (command == null) {
                    continue;
                }
                isBye = command.execute(taskList);
                storage.saveFile(taskList);
            } catch (ElziException e) {
                Ui.printErrorMessage(e.getMessage());
            }

        } while(!isBye);

        Ui.printByeMessage();
    }
    /**
     * Main driver of the code, institates new Elzi program
     */
    public static void main(String[] args) {
        new Elzi("data/input.txt").run();
    }
}
