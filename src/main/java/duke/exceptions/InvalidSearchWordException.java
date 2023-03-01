package duke.exceptions;

import duke.ui.Ui;

public class InvalidSearchWordException extends Exception{
    public static void printErrorMessage(){
        Ui.printBorder();
        System.out.println("ERROR! Input a valid search keyword.");
    }
}
