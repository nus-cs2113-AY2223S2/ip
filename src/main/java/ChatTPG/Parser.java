package ChatTPG;

public class Parser {
    public static void handleCommand(String command) {
        if (command.matches("^list$")) {
            TaskList.listTasks();
        } else if (command.matches("^mark.*$")) {
            try {
                verifyMarkCommand(command);
                int task_number = Integer.parseInt(command.substring(5)) - 1;
                TaskList.markTask(task_number);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("mark <number>");
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println("ERROR: invalid task number");
            }
        } else if (command.matches("^unmark.*$")) {
            try {
                verifyUnmarkCommand(command);
                int task_number = Integer.parseInt(command.substring(7)) - 1;
                TaskList.unmarkTask(task_number);
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
                Deadline deadline = TaskList.createDeadline(command, false);
                TaskList.addToList(deadline);
                TaskList.notifyTaskAdded(deadline);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("deadline <task> /by <due date>");
            } catch (CommandMissingArguments e) {
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            } catch (InvalidDateFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("<YYYY-MM-DD>");
            }
        } else if (command.matches("^event.*$")) {
            try {
                verifyEventCommand(command);
                Event event = TaskList.createEvent(command, false);
                TaskList.addToList(event);
                TaskList.notifyTaskAdded(event);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("event <task> /from <start> /to <end>");
            } catch (CommandMissingArguments e) {
                System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            } catch (InvalidDateFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("<YYYY-MM-DD>");
            } catch (InvalidStartEnd e) {
                System.out.println("ERROR: start date cannot occur after end date.");
            }
        } else if (command.matches("^delete.*$")) {
            try {
                verifyDeleteCommand(command);
                int task_number = Integer.parseInt(command.substring(7)) - 1;
                TaskList.deleteTask(task_number);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("delete <number>");
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println("ERROR: invalid task number");
            }
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void verifyMarkCommand(String command) throws InvalidCommandFormat {
        if (!command.matches("^mark [0-9]*$")) {
            throw new InvalidCommandFormat();
        }
    }

    public static void verifyUnmarkCommand(String command) throws InvalidCommandFormat {
        if (!command.matches("^unmark [0-9]*$")) {
            throw new InvalidCommandFormat();
        }
    }

    public static void verifyTodoCommand(String command)
            throws CommandMissingArguments, InvalidCommandFormat {
        if (command.matches("^todo *$")) {
            throw new CommandMissingArguments();
        }
        else if (!command.matches("^todo .*$")) {
            throw new InvalidCommandFormat();
        }
    }

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

    public static void verifyDeleteCommand(String command) throws InvalidCommandFormat {
        if (!command.matches("^delete [0-9]*$")) {
            throw new InvalidCommandFormat();
        }
    }
}
