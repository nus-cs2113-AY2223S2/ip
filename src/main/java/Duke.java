import java.util.Scanner;
import data.ProcessStorageTasks;
import ui.Display;
import ui.exceptions.MissingCommandException;
import userInputParser.Parser;

/**
 * The Duke program implements an application that allows user to keep track of
 * different types of tasks they have.
 * 
 * @author Aden Teo
 * @version 0.1
 * @since 2023-01-15
 */
public class Duke {
    private static String userInput;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Display.greetUser();
        ProcessStorageTasks.processFile();
        while (scan.hasNextLine()) {
            userInput = scan.nextLine();
            try {
                Parser.parseUserInput(userInput.trim());
            } catch (MissingCommandException e) {
                Display.warnUser(e.getMessage());
            }
        }
        scan.close();
        Display.goodbyeUser();
    }
}
