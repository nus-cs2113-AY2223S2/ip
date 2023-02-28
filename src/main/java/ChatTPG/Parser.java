package ChatTPG;

/**
 * Class which handles user commands and verifies the syntax of the commands passed in.
 */
public class Parser {

    /**
     * Processes the specified user command to perform the relevant actions.
     *
     * @param command User command.
     */
    public static void handleCommand(String command) {
        if (command.matches("^list$")) {
            TaskList.listTasks();
        } else if (command.matches("^mark.*$")) {
            try {
                verifyMarkCommand(command);
                int taskNumber = Integer.parseInt(command.substring(5)) - 1;
                TaskList.markTask(taskNumber);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("mark <number>");
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println("ERROR: invalid task number");
            }
        } else if (command.matches("^unmark.*$")) {
            try {
                verifyUnmarkCommand(command);
                int taskNumber = Integer.parseInt(command.substring(7)) - 1;
                TaskList.unmarkTask(taskNumber);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("mark <number>");
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println("ERROR: invalid task number");
            }
        } else if (command.matches("^todo.*$")) {
            try {
                verifyTodoCommand(command);
                ToDo todo = TaskList.createToDo(command, false);
                TaskList.addToList(todo);
                TaskList.notifyTaskAdded(todo);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("todo <task>");
            } catch (CommandMissingArguments e) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (command.matches("^deadline.*$")) {
            try {
                verifyDeadlineCommand(command);
                Deadline deadline = TaskList.createDeadline(command, false, false);
                TaskList.addToList(deadline);
                TaskList.notifyTaskAdded(deadline);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("deadline <task> /by <due date>");
            } catch (CommandMissingArguments e) {
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            } catch (InvalidDateFormat e) {
                System.out.println("ERROR: date must be of the following form:");
                System.out.println("<YYYY-MM-DD>");
            }
        } else if (command.matches("^event.*$")) {
            try {
                verifyEventCommand(command);
                Event event = TaskList.createEvent(command, false, false);
                TaskList.addToList(event);
                TaskList.notifyTaskAdded(event);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("event <task> /from <start> /to <end>");
            } catch (CommandMissingArguments e) {
                System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            } catch (InvalidDateFormat e) {
                System.out.println("ERROR: dates must be of the following form:");
                System.out.println("<YYYY-MM-DD>");
            } catch (InvalidStartEnd e) {
                System.out.println("ERROR: start date cannot occur after end date.");
            }
        } else if (command.matches("^delete.*$")) {
            try {
                verifyDeleteCommand(command);
                int taskNumber = Integer.parseInt(command.substring(7)) - 1;
                TaskList.deleteTask(taskNumber);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("delete <number>");
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println("ERROR: invalid task number");
            }
        } else if (command.matches("^find.*$")) {
            try {
                verifyFindCommand(command);
                String keyword = command.substring(5);
                TaskList.findTasks(keyword);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("find <keyword>");
            } catch (CommandMissingArguments e) {
                System.out.println("☹ OOPS!!! The keyword of a find cannot be empty.");
            }
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Verifies the syntax of the mark command passed in.
     *
     * @param command User command.
     * @throws InvalidCommandFormat if syntax of command is wrong.
     */
    public static void verifyMarkCommand(String command) throws InvalidCommandFormat {
        if (!command.matches("^mark [0-9]*$")) {
            throw new InvalidCommandFormat();
        }
    }

    /**
     * Verifies the syntax of the unmark command passed in.
     *
     * @param command User command.
     * @throws InvalidCommandFormat if syntax of command is wrong.
     */
    public static void verifyUnmarkCommand(String command) throws InvalidCommandFormat {
        if (!command.matches("^unmark [0-9]*$")) {
            throw new InvalidCommandFormat();
        }
    }

    /**
     * Verifies the syntax of the todo command passed in.
     *
     * @param command User command.
     * @throws CommandMissingArguments if no arguments are detected after the todo keyword.
     * @throws InvalidCommandFormat if syntax of command is wrong.
     */
    public static void verifyTodoCommand(String command)
            throws CommandMissingArguments, InvalidCommandFormat {
        if (command.matches("^todo *$")) {
            throw new CommandMissingArguments();
        }
        else if (!command.matches("^todo .*$")) {
            throw new InvalidCommandFormat();
        }
    }

    /**
     * Verifies the syntax of the deadline command passed in.
     *
     * @param command User command.
     * @throws CommandMissingArguments if no arguments are detected after the deadline keyword.
     * @throws InvalidCommandFormat if syntax of command is wrong.
     */
    public static void verifyDeadlineCommand(String command)
            throws CommandMissingArguments, InvalidCommandFormat {
        if (command.matches("^deadline *$")) {
            throw new CommandMissingArguments();
        }
        else if (!command.matches("^deadline .*$")) {
            throw new InvalidCommandFormat();
        }
        else if (!command.contains("/by")) {
            throw new InvalidCommandFormat();
        }
    }

    /**
     * Verifies the syntax of the event command passed in.
     *
     * @param command User command.
     * @throws CommandMissingArguments if no arguments are detected after the event keyword.
     * @throws InvalidCommandFormat if syntax of command is wrong.
     */
    public static void verifyEventCommand(String command)
            throws CommandMissingArguments, InvalidCommandFormat {
        if (command.matches("^event *$")) {
            throw new CommandMissingArguments();
        }
        else if (!command.matches("^event .*$")) {
            throw new InvalidCommandFormat();
        }
        else if (!(command.contains("/from") && command.contains("/to"))) {
            throw new InvalidCommandFormat();
        }
    }

    /**
     * Verifies the syntax of the delete command passed in.
     *
     * @param command User command.
     * @throws InvalidCommandFormat if syntax of command is wrong.
     */
    public static void verifyDeleteCommand(String command) throws InvalidCommandFormat {
        if (!command.matches("^delete [0-9]*$")) {
            throw new InvalidCommandFormat();
        }
    }

    /**
     * Verifies the syntax of the find command passed in.
     *
     * @param command User command.
     * @throws CommandMissingArguments if no arguments are detected after the find keyword.
     * @throws InvalidCommandFormat if syntax of command is wrong.
     */
    public static void verifyFindCommand(String command)
            throws CommandMissingArguments, InvalidCommandFormat {
        if (command.matches("^find *$")) {
            throw new CommandMissingArguments();
        }
        else if (!command.matches("^find .*$")) {
            throw new InvalidCommandFormat();
        }
    }
}
