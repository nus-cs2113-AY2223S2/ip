package utility;

public class commandChecker {
    private final String[] decisions;
    private final String[] dates;
    private final int actionCounter;
    private boolean hasErrorFlags = false;

    public commandChecker(String[] decisions, String[] dates, int actionCounter) {
        this.decisions = decisions;
        this.dates = dates;
        this.actionCounter = actionCounter;
    }

    public boolean hasErrors() {
        try {
            validateCommand();
        } catch (DukeException e) {
            this.hasErrorFlags = true;
            if (!e.getDescription().equals("No Description")) {
                Ui.print("Invalid input, please try again! Error Description: " + e.getDescription() + System.lineSeparator());
            }
        } finally {
            return this.hasErrorFlags;
        }
    }

    private void validateCommand() throws DukeException {
        DukeException currentException = new DukeException();
        switch (decisions[0]) {
            case "echo":
                validateEcho(currentException);
                break;
            case "todo":
                validateToDo(currentException);
                break;
            case "event":
                validateEvent(currentException);
                break;
            case "deadline":
                validateDeadline(currentException);
                break;
            case "mark":
                validateMarkTask(currentException);
                break;
            case "unmark":
                validateUnmarkTask(currentException);
                break;
            case "list":
                validateList(currentException);
                break;
            case "delete":
                validateDeleteTask(currentException);
                break;
            case "find":
                validateFindTask(currentException);
                break;
            case "bye":
                break;
            default:
                currentException.setDescription("Invalid action word! e.g. echo, todo, list, etc.");
                throw currentException;
        }
    }

    private void validateEcho(DukeException currentException) throws DukeException {
        if (decisions.length < 2) {
            currentException.setDescription("echo description cannot be empty!");
            throw currentException;
        }
    }

    private void validateToDo(DukeException currentException) throws DukeException {
        if (decisions.length < 2) {
            currentException.setDescription("todo description cannot be empty!");
            throw currentException;
        }
    }

    private void validateEvent(DukeException currentException) throws DukeException {
        if (dates.length < 3) {
            currentException.setDescription("event needs to have exactly two dates, /from date /to date");
            throw currentException;
        }
    }

    private void validateDeadline(DukeException currentException) throws DukeException {
        if (dates.length < 2) {
            currentException.setDescription("deadline needs to have exactly one date, /by");
            throw currentException;
        }
    }

    private void validateMarkTask(DukeException currentException) throws DukeException {
        if (decisions.length < 2) {
            currentException.setDescription("the description of the task you wish to mark is empty!");
            throw currentException;
        } else if (decisions.length > 2) {
            currentException.setDescription("you may only mark one task at a time");
            throw currentException;
        }
        if (actionCounter == 0) {
            currentException.setDescription("list is currently empty!");
            throw currentException;
        }
        if (Integer.parseInt(decisions[1]) > actionCounter || Integer.parseInt(decisions[1]) < 1) {
            currentException.setDescription("out of bounds!");
            throw currentException;
        }
    }

    private void validateUnmarkTask(DukeException currentException) throws DukeException {
        if (decisions.length < 2) {
            currentException.setDescription("the description of the task you wish to unmark is empty!");
            throw currentException;
        } else if (decisions.length > 2) {
            currentException.setDescription("you may only unmark one task at a time");
            throw currentException;
        }
        if (actionCounter == 0) {
            currentException.setDescription("list is currently empty!");
            throw currentException;
        }
        if (Integer.parseInt(decisions[1]) > actionCounter || Integer.parseInt(decisions[1]) < 1) {
            currentException.setDescription("out of bounds!");
            throw currentException;
        }
    }

    private void validateList(DukeException currentException) throws DukeException {
        if (actionCounter == 0) {
            currentException.setDescription("list is currently empty!");
            throw currentException;
        }
    }

    private void validateDeleteTask(DukeException currentException) throws DukeException {
        if (decisions.length < 2) {
            currentException.setDescription("the description of the task you wish to delete is empty!");
            throw currentException;
        } else if (decisions.length > 2) {
            currentException.setDescription("you may only delete one task at a time");
            throw currentException;
        }
        if (actionCounter == 0) {
            currentException.setDescription("list is currently empty!");
            throw currentException;
        }
        if (Integer.parseInt(decisions[1]) > actionCounter || Integer.parseInt(decisions[1]) < 1) {
            currentException.setDescription("out of bounds!");
            throw currentException;
        }
    }
    private void validateFindTask(DukeException currentException) throws DukeException {
        if (decisions.length < 2) {
            currentException.setDescription("the description of the task you wish to find cannot be empty!");
            throw currentException;
        }
        if (actionCounter == 0) {
            currentException.setDescription("list is currently empty!");
            throw currentException;
        }
    }

}

