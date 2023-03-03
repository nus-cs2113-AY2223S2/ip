package utility;

/**
 * Represents a class used to check for proper usage of commands.
 * It serves to accept the inputs after it has been broken down, and check for relevant fields to be filled.
 * When an error is detected, it will throw a custom exception: DukeException, which contains the relevant information.
 */
public class commandChecker {
    private static final String DEFAULT_EMPTY_DESCRIPTION = "No Description";
    private static final String DEFAULT_CAUGHT_ERROR_MESSAGE = "Invalid input, please try again! Error Description: ";
    private static final String DEFAULT_INPUT_ERROR_MESSAGE = "Invalid action word! e.g. echo, todo, list, etc.";
    private static final String DEFAULT_MISSING_DESCRIPTION_MESSAGE = " description cannot be empty!";
    private static final String DEFAULT_EVENT_ERROR_MESSAGE = "event needs a description and /from date /to date";
    private static final String DEFAULT_DEADLINE_ERROR_MESSAGE = "deadline needs to have a description and /by date";
    private static final String DEFAULT_MARKING_ERROR = "you may only mark or unmark one task at a time";
    private static final String DEFAULT_LIST_EMPTY_ERROR = "list is currently empty!";
    private static final String DEFAULT_DELETE_ERROR = "you may only delete one task at a time";
    private static final String DEFAULT_OUT_OF_BOUND_ERROR = "out of bounds!";

    private final String[] decisions;

    private final String[] dates;

    private final int actionCounter;

    private boolean hasErrorFlags = false;

    /**
     * Initialise the commandChecker class. It takes in filtered and broken down inputs that are parsed through
     * by DukeSession.
     *
     * @param decisions     The parsed input that contains important information about the type of action.
     * @param dates         The parsed input that contains important information about the dates of the task.
     * @param actionCounter The current size of the list action.
     */
    public commandChecker(String[] decisions, String[] dates, int actionCounter) {
        this.decisions = decisions;
        this.dates = dates;
        this.actionCounter = actionCounter;
    }

    /**
     * Returns a flag of whether errors have been detected in the user's input.
     * It calls the method validate command, which will be throwing the exception DukeException.
     * This method is called for every user input in DukeSession.
     *
     * @return True or False of whether there are any errors detected.
     */
    public boolean hasErrors() {
        try {
            validateCommand();
        } catch (DukeException e) {
            this.hasErrorFlags = true;
            boolean isErrorMessageSameAsDefault = e.getDescription().equals(DEFAULT_EMPTY_DESCRIPTION);
            if (!isErrorMessageSameAsDefault) {
                Ui.print(DEFAULT_CAUGHT_ERROR_MESSAGE + e.getDescription() + System.lineSeparator());
            }
        }
        return this.hasErrorFlags;
    }

    /**
     * Validates the user input according to the correct action word.
     * It will throw an exception when the custom exception class DukeException is captured.
     *
     * @throws DukeException if input has an incorrect action word or missing description or dates.
     */
    private void validateCommand() throws DukeException {
        DukeException currentException = new DukeException();
        switch (decisions[0]) {
        case Ui.DEFAULT_ECHO:
            validateEcho(currentException);
            break;
        case Ui.DEFAULT_TODO:
            validateToDo(currentException);
            break;
        case Ui.DEFAULT_EVENT:
            validateEvent(currentException);
            break;
        case Ui.DEFAULT_DEADLINE:
            validateDeadline(currentException);
            break;
        case Ui.DEFAULT_MARK_TASK:
            validateMarkTask(currentException);
            break;
        case Ui.DEFAULT_UNMARK_TASK:
            validateUnmarkTask(currentException);
            break;
        case Ui.DEFAULT_LIST_ALL_TASKS:
            validateList(currentException);
            break;
        case Ui.DEFAULT_DELETE:
            validateDeleteTask(currentException);
            break;
        case Ui.DEFAULT_FIND:
            validateFindTask(currentException);
            break;
        case Ui.DEFAULT_EXIT:
            break;
        default:
            currentException.setDescription(DEFAULT_INPUT_ERROR_MESSAGE);
            throw currentException;
        }
    }

    private void validateEcho(DukeException currentException) throws DukeException {
        if (decisions.length < 2) {
            currentException.setDescription(Ui.DEFAULT_ECHO + DEFAULT_MISSING_DESCRIPTION_MESSAGE);
            throw currentException;
        }
    }

    private void validateToDo(DukeException currentException) throws DukeException {
        if (decisions.length < 2) {
            currentException.setDescription(Ui.DEFAULT_TODO + DEFAULT_MISSING_DESCRIPTION_MESSAGE);
            throw currentException;
        }
    }

    private void validateEvent(DukeException currentException) throws DukeException {
        if (dates.length < 3) {
            currentException.setDescription(DEFAULT_EVENT_ERROR_MESSAGE);
            throw currentException;
        }
    }

    private void validateDeadline(DukeException currentException) throws DukeException {
        if (dates.length < 2) {
            currentException.setDescription(DEFAULT_DEADLINE_ERROR_MESSAGE);
            throw currentException;
        }
    }

    private void validateMarkTask(DukeException currentException) throws DukeException {
        if (decisions.length < 2) {
            currentException.setDescription(Ui.DEFAULT_MARK_TASK + DEFAULT_MISSING_DESCRIPTION_MESSAGE);
            throw currentException;
        } else if (decisions.length > 2) {
            currentException.setDescription(DEFAULT_MARKING_ERROR);
            throw currentException;
        }
        if (actionCounter == 0) {
            currentException.setDescription(DEFAULT_LIST_EMPTY_ERROR);
            throw currentException;
        }
        if (Integer.parseInt(decisions[1]) > actionCounter || Integer.parseInt(decisions[1]) < 1) {
            currentException.setDescription(DEFAULT_OUT_OF_BOUND_ERROR);
            throw currentException;
        }
    }

    private void validateUnmarkTask(DukeException currentException) throws DukeException {
        if (decisions.length < 2) {
            currentException.setDescription(Ui.DEFAULT_UNMARK_TASK + DEFAULT_MISSING_DESCRIPTION_MESSAGE);
            throw currentException;
        } else if (decisions.length > 2) {
            currentException.setDescription(DEFAULT_MARKING_ERROR);
            throw currentException;
        }
        if (actionCounter == 0) {
            currentException.setDescription(DEFAULT_LIST_EMPTY_ERROR);
            throw currentException;
        }
        if (Integer.parseInt(decisions[1]) > actionCounter || Integer.parseInt(decisions[1]) < 1) {
            currentException.setDescription(DEFAULT_OUT_OF_BOUND_ERROR);
            throw currentException;
        }
    }

    private void validateList(DukeException currentException) throws DukeException {
        if (actionCounter == 0) {
            currentException.setDescription(DEFAULT_LIST_EMPTY_ERROR);
            throw currentException;
        }
    }

    private void validateDeleteTask(DukeException currentException) throws DukeException {
        if (decisions.length < 2) {
            currentException.setDescription(Ui.DEFAULT_DELETE + DEFAULT_MISSING_DESCRIPTION_MESSAGE);
            throw currentException;
        } else if (decisions.length > 2) {
            currentException.setDescription(DEFAULT_DELETE_ERROR);
            throw currentException;
        }
        if (actionCounter == 0) {
            currentException.setDescription(DEFAULT_LIST_EMPTY_ERROR);
            throw currentException;
        }
        if (Integer.parseInt(decisions[1]) > actionCounter || Integer.parseInt(decisions[1]) < 1) {
            currentException.setDescription(DEFAULT_OUT_OF_BOUND_ERROR);
            throw currentException;
        }
    }

    private void validateFindTask(DukeException currentException) throws DukeException {
        if (decisions.length < 2) {
            currentException.setDescription(Ui.DEFAULT_FIND + DEFAULT_MISSING_DESCRIPTION_MESSAGE);
            throw currentException;
        }
        if (actionCounter == 0) {
            currentException.setDescription(DEFAULT_LIST_EMPTY_ERROR);
            throw currentException;
        }
    }

}

