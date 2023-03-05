package buddy;

import java.util.Scanner;

import buddy.parser.Parser;
import buddy.storage.Storage;
import buddy.tasks.TaskList;
import buddy.ui.Ui;

/**
 * The part which executes the entire program
 */
public class Buddy {
    private final Storage storage;
    private static TaskList taskList;
    private final Ui ui;
    public static int taskCount = 0;

    /**
     * Constructor for Buddy class
     *
     * @param filePath The filepath of the file
     */
    public Buddy(String filePath) {
        taskList = new TaskList();
        storage = new Storage(filePath);
        ui = new Ui();
    }

    /**
     * This function runs the program till completion (till a "bye" is inputted)
     */
    public void run() {
        ui.loadFileOrCreateFile(taskList, storage);
        ui.greetUser();
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        Parser processAllCommands = new Parser();

        while (!processAllCommands.isExit(input)) {
            processAllCommands.executeInput(taskList, input, storage);
            input = in.nextLine();
        }
        ui.sayByeToUser();
    }
    

    public static void main(String[] args) {
        new Buddy("./Data").run();
    }
}
