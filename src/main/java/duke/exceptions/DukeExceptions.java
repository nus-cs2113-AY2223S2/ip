package duke.exceptions;

/**
 * Parent class for all types of exceptions.
 */
public abstract class DukeExceptions extends Exception {
    
    private String message;

    private static final String BARRIER = "____________________________________________________________";

    public DukeExceptions(String errorMsg) {
        this.message = errorMsg;
    }

    public String getMessage() {
        return this.message;
    }
    /**
     * Occurs when user attempts to print the task list with no tasks
     */
    public static class noTasksException extends DukeExceptions {
        public noTasksException(String errorMsg) {
            super(errorMsg);
            System.out.println(BARRIER + "\n\n" + errorMsg);
        }
    }
    /**
     * Occurs when user attempts to submit non-numeric input for a 
     * method that is only meant to accept numeric input. For example,
     * trying to execute 'mark Duke' will throw the error.
     */
    public static class invalidNumberException extends DukeExceptions {
        public invalidNumberException(String errorMsg) {
            super(errorMsg);
            System.out.println(errorMsg);
        }
    }

    /**
     * Occurs when user attempts to mark a completed task as complete,
     * or incomplete task as incomplete.
     */
    public static class taskStatusException extends DukeExceptions {
        public taskStatusException(String errorMsg) {
            super(errorMsg);
            System.out.println(errorMsg);
        }
    }

    /**
     * Occurs when user attempts to execute a command with invalid input
     * syntax. For example, the command 'event' must have both a start and
     * end time delineated by /to and /from respectively. If either is missing,
     * this error will be thrown.
     */
    public static class invalidInputStructure extends DukeExceptions {
        public invalidInputStructure(String errorMsg) {
            super(errorMsg);
            System.out.println(BARRIER + "\n\n" + errorMsg + "\n" + BARRIER + "\n");
        }
    }
}