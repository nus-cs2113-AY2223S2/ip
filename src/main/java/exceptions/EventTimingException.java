package exceptions;

import ui.Ui;

public class EventTimingException extends Exception {
    Ui ui = new Ui();

    /**
     * Prints out error message for invalid event timing.
     */
    public void printError() {
        ui.showEventTimingExceptionError();
    }
}
