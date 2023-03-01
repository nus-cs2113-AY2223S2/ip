package duke.exceptions;

import duke.ui.Ui;

public class UncheckedExceptionHandler {
    public static void printInvalidDescriptionMessage(){
        Ui.printBorder();
        System.out.println("OOPS! The description of task is invalid");
        Ui.printBorder();
    }
    public static void printInvalidTaskIndexMessage(){
        Ui.printBorder();
        System.out.println("Error! Specify a valid task index!");
        Ui.printBorder();
    }
}
