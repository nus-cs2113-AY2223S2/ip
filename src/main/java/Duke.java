import java.util.Scanner;


/**
 * Represents a task tracker named Duke. A <code>Duke</code> object uses
 * methods from various methods to execute given commands smoothly.
 */
public class Duke {
    public static boolean continueRunning = true;

    /**
     * Loads in data from a pre-existing data file. If none is present, proceeds to create a new empty data file.
     * @param filePath Location in which the data file can be found.
     */
    public Duke(String filePath){
        Storage storage = new Storage(filePath);
        storage.load();
    }
    public static void main(String[] args) {
        new Duke ("./data/taskList.txt").run();
    }

    /**
     * Central loop that ensures that Duke remains running until user inputs the <code>bye</code> command.
     */
    public static void run() {
        UI.greet();
        while (continueRunning) {
            Scanner newScanner = new Scanner(System.in);
            String userInput = newScanner.nextLine();
            String[] userInputSplit = userInput.split(" ", 2);
            Parser controller = new Parser(userInputSplit);
            controller.processInput();
        }
    }
}


