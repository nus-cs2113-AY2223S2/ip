package ui;

import common.Messages;

/**
 * UI to print messages.
 */
public class Ui {
    public void showWelcomeMessage() {
        showToUser(Messages.MESSAGE_WELCOME);
    }

    public void showByeMessage() {
        showToUser(Messages.MESSAGE_GOODBYE);
    }

    public void showTaskTypeExceptionError() {
        showToUser(Messages.TASK_TYPE_EXCEPTION_ERROR);
    }

    public void showEventTimingExceptionError() {
        showToUser(Messages.EVENT_TIMING_EXCEPTION_ERROR);
    }

    public void showInsufficientInputError() {
        showToUser(Messages.INSUFFICIENT_INPUT_ERROR);
    }

    public void showNumberFormatError() {
        showToUser(Messages.NUMBER_FORMAT_EXCEPTION_ERROR);
    }

    public void showToUser(String text) {
        System.out.println(text);
    }

}
