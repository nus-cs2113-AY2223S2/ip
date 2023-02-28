package ChatTPG;

import java.util.ArrayList;

public class TaskManager {

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int task_count = 0;

    public static void initialize() {
        tasks = Data.loadData();
    }

    public static void end() {
        Data.saveData(tasks);
    }

    public static void handleCommand(String command) {
        if (command.matches("^list$")) {
            listTasks();
        } else if (command.matches("^mark.*$")) {
            try {
                verifyMarkCommand(command);
                int task_number = Integer.parseInt(command.substring(5)) - 1;
                markTask(task_number);
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
                unmarkTask(task_number);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("mark <number>");
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println("ERROR: invalid task number");
            }
        } else if (command.matches("^todo.*$")) {
            try {
                verifyTodoCommand(command);
                ToDo todo = createToDo(command, false);
                tasks = addToList(tasks, todo);
                notifyTaskAdded(todo);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("todo <task>");
            } catch (CommandMissingArguments e) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (command.matches("^deadline.*$")) {
            try {
                verifyDeadlineCommand(command);
                Deadline deadline = createDeadline(command, false);
                tasks = addToList(tasks, deadline);
                notifyTaskAdded(deadline);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("deadline <task> /by <due date>");
            } catch (CommandMissingArguments e) {
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (command.matches("^event.*$")) {
            try {
                verifyEventCommand(command);
                Event event = createEvent(command, false);
                tasks = addToList(tasks, event);
                notifyTaskAdded(event);
            } catch (InvalidCommandFormat e) {
                System.out.println("ERROR: command must be of the following form:");
                System.out.println("event <task> /from <start> /to <end>");
            } catch (CommandMissingArguments e) {
                System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            }
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static ToDo createToDo(String command, boolean isDone) {
        ToDo todo = new ToDo(command.substring(5), isDone);
        return todo;
    }

    public static Deadline createDeadline(String command, boolean isDone) {
        int separator_idx = command.indexOf("/by");
        String description = command.substring(9, separator_idx - 1);
        String by = command.substring(separator_idx + 4);
        Deadline deadline = new Deadline(description, isDone, by);
        return deadline;
    }

    public static Event createEvent(String command, boolean isDone) {
        int from_idx = command.indexOf("/from");
        int to_idx = command.indexOf("/to");
        String description = command.substring(6, from_idx - 1);
        String from = command.substring(from_idx + 6, to_idx - 1);
        String to = command.substring(to_idx + 4);
        Event event = new Event(description, isDone, from, to);
        return event;
    }

    public static ArrayList<Task> addToList(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        task_count++;
        return tasks;
    }

    public static void notifyTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        displayNumberOfTasks();
    }

    public static void displayNumberOfTasks() {
        if (task_count == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.\n", task_count);
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

    public static void markTask(int task_number) {
        tasks.get(task_number).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(task_number).toString());
    }

    public static void unmarkTask(int task_number) {
        tasks.get(task_number).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(task_number).toString());
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int idx = 0; idx < task_count; idx++) {
            System.out.println(tasks.get(idx).toString());
        }
    }
}
