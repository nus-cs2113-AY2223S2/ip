public class commandChecker {
    private final String[] decisions;
    private final String[] dates;
    private final int actionCounter;
    boolean hasErrorFlags = false;
    public commandChecker(String[] decisions, String[] dates, int actionCounter) {
        this.decisions = decisions;
        this.dates = dates;
        this.actionCounter = actionCounter;
    }
    public boolean hasErrors(){
        try {
            validateCommand();
        } catch (DukeException e) {
            this.hasErrorFlags = true;
            if (!e.getDescription().equals("No Description")) {
                Methods.print("Invalid input, please try again! Error Description: " + e.getDescription() + System.lineSeparator());
            }
        } finally {
            return this.hasErrorFlags;
        }
    }
    public void validateCommand() throws DukeException {
        DukeException currentException = new DukeException();
        switch (decisions[0]) {
            case "echo":
                if (decisions.length < 2) {
                    currentException.setDescription("echo description cannot be empty!");
                    throw currentException;
                }
                break;
            case "todo":
                if (decisions.length < 2) {
                    currentException.setDescription("todo description cannot be empty!");
                    throw currentException;
                }
                break;

            case "event":
                if (dates.length < 3) {
                    currentException.setDescription("event needs to have exactly two dates, /from date /to date");
                    throw currentException;
                }
                break;

            case "deadline":
                if (dates.length < 2) {
                    currentException.setDescription("deadline needs to have exactly one date, /by");
                    throw currentException;
                }
                break;

            case "mark":
                if (decisions.length < 2) {
                    currentException.setDescription("task you wish to mark is empty!");
                    throw currentException;
                } else if (decisions.length > 2) {
                    currentException.setDescription("you may only mark one task at a time");
                    throw currentException;
                }
                break;

            case "unmark":
                if (decisions.length < 2) {
                    currentException.setDescription("task you wish to unmark is empty!");
                    throw currentException;
                } else if (decisions.length > 2) {
                    currentException.setDescription("you may only unmark one task at a time");
                    throw currentException;
                }
                break;

            case "list":
                if (actionCounter == 0) {
                    currentException.setDescription("list is currently empty!");
                    throw currentException;
                }
                break;
            default:
                currentException.setDescription("Invalid action word! e.g. echo, todo, list, etc.");
                throw currentException;
        }
    }
}
