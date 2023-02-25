package exceptions;
import ui.Ui;

public class TaskTypeException extends Exception{
    Ui ui = new Ui();
    public void printError() {
        ui.showTaskTypeExceptionError();
    }
}
