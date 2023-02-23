package duke.functionalities;

import duke.exception.DukeException;

/**
 * This Class deals with making sense of the User Command
 * */
public class Parser {
    protected static Command command;
    protected static String description;
    protected static String notApplicable = null;
    protected static int byIndex;
    protected static int fromIndex;
    protected static int toIndex;

    /**
     * Interprets the User Command to be Executed
     *
     * @param userCommand The Command to be interpreted
     * @throws DukeException if there is an error encountered when interpreting the Duke Command
     * @return The Interpreted Duke Command
     * */
    public static Command parse(String userCommand) throws DukeException {
        String task = userCommand.trim();
        if (task.equalsIgnoreCase("list")) {
            userCommand = "list";
            command = new Command(userCommand, notApplicable, notApplicable, notApplicable, notApplicable);
        } else if (task.toLowerCase().startsWith("mark")) {
            try {
                userCommand = "mark";
                int taskToMarkIndex = 5;
                description = task.substring(taskToMarkIndex);
                command = new Command(userCommand, description, notApplicable, notApplicable, notApplicable);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(" Task to mark is not recognized!");
            }
        } else if (task.toLowerCase().startsWith("unmark")) {
            try {
                userCommand = "unmark";
                int taskToUnmarkIndex = 7;
                description = task.substring(taskToUnmarkIndex);
                command = new Command(userCommand, description, notApplicable, notApplicable, notApplicable);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(" Task to unmark is not recognized!");
            }
        } else if (task.toLowerCase().startsWith("todo")) {
            try {
                userCommand = "todo";
                int todoIndex = 5;
                description = task.substring(todoIndex);
                command = new Command(userCommand, description, notApplicable, notApplicable, notApplicable);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException(" The todo description cannot be empty!");
            }
        } else if (task.toLowerCase().startsWith("deadline")) {
            try {
                userCommand = "deadline";
                int descriptionIndex = 9;
                byIndex = task.indexOf("/by");
                description = task.substring(descriptionIndex, byIndex);
                int deadlineIndex = byIndex + 4;
                String deadline = task.substring(deadlineIndex);
                command = new Command(userCommand, description, deadline, notApplicable, notApplicable);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException(" The entered deadline description is invalid!");
            }
        } else if (task.toLowerCase().startsWith("event")) {
            try {
                userCommand = "event";
                int descriptionIndex = 6;
                fromIndex = task.indexOf("/from");
                toIndex = task.indexOf("/to");
                description = task.substring(descriptionIndex, fromIndex);
                int eventStartIndex = fromIndex + 6;
                String eventStart = task.substring(eventStartIndex, toIndex);
                int eventEndIndex = toIndex + 4;
                String eventEnd = task.substring(eventEndIndex);
                command = new Command(userCommand, description, notApplicable, eventStart, eventEnd);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException(" The entered event description is invalid!");
            }
        } else if (task.toLowerCase().startsWith("delete")) {
            try {
                userCommand = "delete";
                int taskToDeleteIndex = 7;
                description = task.substring(taskToDeleteIndex);
                command = new Command(userCommand, description, notApplicable, notApplicable, notApplicable);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException(" Task to delete does not exist in the list!");
            }
        } else if (task.toLowerCase().startsWith("find")) {
            try {
                userCommand = "find";
                int findIndex = 5;
                description = task.substring(findIndex);
                command = new Command(userCommand, description, notApplicable, notApplicable, notApplicable);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException(" The find description cannot be empty!");
            }
        } else if (task.equalsIgnoreCase("bye")) {
            userCommand = "bye";
            command = new Command(userCommand, notApplicable, notApplicable, notApplicable, notApplicable);
        } else {
            throw new DukeException(" Not a recognized Duke command!");
        }
        return command;
    }
}