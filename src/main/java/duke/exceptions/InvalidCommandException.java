package duke.exceptions;

import duke.ui.Ui;

public class InvalidCommandException extends Exception{
    public void printErrorMessage(){
        Ui.printBorder();
        System.out.println("OOPS! I'm sorry, but I don't know what that means :-(");
    }
}
