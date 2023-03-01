package duke.exceptions;

import duke.ui.Ui;

public class EmptyTaskException extends Exception {
    public void printErrorMessage(){
        Ui.printBorder();
        System.out.println("OOPS! The description of task cannot be empty");
    }
}
