import java.util.Scanner;
import data.ProcessSavedTasks;
import ui.Display;
import userInputParser.Parser;

public class Duke {
    private static String userInput;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Display.greetUser();
        ProcessSavedTasks.processFile();
        while (scan.hasNextLine()) {
            userInput = scan.nextLine();
            Parser.parseUserInput(userInput);
        }
        scan.close();
        Display.goodbyeUser();
    }
}
