package sage.main;

import sage.utility.Display;
import sage.utility.Parser;
import sage.utility.Ui;


public class Sage {
    public static void main(String[] args) {
        Display display = new Display();
        Ui ui = new Ui();
        ui.recoverData();
        boolean isExit = false;
        display.printWelcomeUser();
        while (!isExit) {
            String input = ui.readInput();
            Parser parsed = new Parser(input);
            isExit = ui.readCommand(parsed);
        }
    }
}
