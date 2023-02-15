import java.util.Scanner;
import data.ProcessStorageTasks;
import ui.Display;
import userInputParser.Parser;

public class Duke {
    private static String userInput;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Display.greetUser();
        ProcessStorageTasks.processFile();
        while (scan.hasNextLine()) {
            userInput = scan.nextLine();
            Parser.parseUserInput(userInput.trim());
        }
        scan.close();
        Display.goodbyeUser();
    }
}
