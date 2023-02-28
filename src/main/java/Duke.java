import java.util.Scanner;
import java.io.IOException;

import duke.Parser;
import duke.Storage;
import duke.TaskManager;
import duke.Ui;

public class Duke {

    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        ui.printGreeting();
        Scanner scanObj = new Scanner(System.in);
        TaskManager listOfItems = new TaskManager();
        listOfItems = Storage.loadFile(listOfItems);
        String userCmd = scanObj.nextLine();
        while (!userCmd.equals("bye")) {
            Parser.handleCmd(userCmd, listOfItems, ui);
            userCmd = scanObj.nextLine();
        }
        scanObj.close();
        ui.printGoodbye();
    }

}
