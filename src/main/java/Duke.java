import java.util.Scanner;
import duke.Ui;
import java.io.IOException;


public class Duke {

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
