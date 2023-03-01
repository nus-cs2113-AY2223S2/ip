package exceptions;
import ui.Ui;

public class TaskTypeException extends Exception{
    Ui ui = new Ui();

    /**
     * Prints out error message for invalid task type.
     */
    public void printError() {
        ui.showTaskTypeExceptionError();
    }
}
