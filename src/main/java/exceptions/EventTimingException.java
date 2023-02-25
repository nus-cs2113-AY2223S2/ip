package exceptions;

import ui.Ui;

public class EventTimingException extends Exception {
    Ui ui = new Ui();

    public void printError() {
        ui.showEventTimingExceptionError();
    }
}
