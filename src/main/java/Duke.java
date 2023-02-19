import java.util.Scanner;
import duke.Ui;
import java.io.IOException;

/**
 * Main class for Duke, a Command Line Application that helps the user keep track of his or her tasks.
 * The user can add, delete, mark as done, and list his or her tasks.
 * The user can also search for tasks by keyword.
 * The user can also add deadlines and events to his or her tasks.
 */

public class Duke {

    /**
     * Main method for Duke to run the application.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String command;
        String lowerCaseCommand;
        Scanner in = new Scanner(System.in);
        Storage.readFile();
        Ui.printLine();
        Ui.printWelcomeMessage();
        Ui.printLine();
        boolean isExit = false;
        while(!isExit) {
            command = in.nextLine();
            lowerCaseCommand = command.toLowerCase();
            Ui.printLine();
            Parser.parse(lowerCaseCommand);
            Ui.printLine();
            isExit = Parser.isExit();
        }
    }
}
