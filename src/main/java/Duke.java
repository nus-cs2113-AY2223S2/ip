import duke.exception.CommandNotFoundException;
import duke.exception.IllegalFormatException;
import duke.exception.InvalidInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    protected static ArrayList<Task>tasks = new ArrayList<>();

    /**
     * Represents a boolean that will switch to false upon the "bye" command.
     * The loop for command input will continue so long as it is not terminated by this boolean function.
     */
    protected static boolean proceedToNextCommand = true;
    protected static void printDivider() {
        String DIVIDER = "____________________________________________________";
        System.out.println(DIVIDER);
    }

    /**
     * This function will determine the command that was given by the user input and execute the
     * commands accordingly to its specified usage.
     *
     * @param input Input string that is provided by user in the CLI.
     * @throws CommandNotFoundException if command is not found as specified.
     */
    protected static void executeCommand(String input) throws CommandNotFoundException {
        if (input.equals("bye")) {
            printExit();
            proceedToNextCommand = false;
            System.exit(0);
        } else if (input.startsWith("mark")) {
            printDivider();
            try {
                markTask(input);
            } catch (NumberFormatException e) {
                System.out.println("Task to be marked is not a number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task number to be marked is not within the list");
            }
            printDivider();
        } else if (input.startsWith("unmark")) {
            printDivider();
            try {
                unmarkTask(input);
            } catch (NumberFormatException e) {
                System.out.println("Task to be unmarked is not a number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task number to be unmarked is not within the list");
            }
            printDivider();
        } else if (input.equals("list")) {
            printList();
        } else if (input.startsWith("todo")) {
            printDivider();
            try {
                createTodo(input);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The description of a todo cannot be empty.");
            }
            printDivider();
        } else if (input.startsWith("deadline")) {
            printDivider();
            try {
                createDeadline(input);
            } catch (IndexOutOfBoundsException | InvalidInputException e) {
                System.out.println("Incomplete description or date of the deadline\n" + "Please input in the following format: deadline <description> /by <when>");
            }
            printDivider();
        } else if (input.startsWith("event")) {
            printDivider();
            try {
                createEvent(input);
            } catch (IllegalFormatException e) {
                System.out.println("The /from cannot go after /to\n" + "Please input in the following format: event <description> /from <when> /to <when>");
            } catch (IndexOutOfBoundsException | InvalidInputException e) {
                System.out.println("Incomplete description or date of the event\n" + "Please input in the following format: event <description> /from <when> /to <when>");
            }
            printDivider();
        } else if (input.startsWith("delete")) {
            printDivider();
            try {
                deleteTask(input);
            } catch (NumberFormatException e) {
                System.out.println("Task to be deleted is not a number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task number to be deleted is not within the list");
            }
            printDivider();
        } else if (input.startsWith("find")) {
            printDivider();
            try {
                findTask(input);
            } catch (IllegalFormatException e) {
                System.out.println("The keyword of the task cannot be empty.");
            } catch (InvalidInputException e) {
                System.out.println("No such task found in the list.");
            }
            printDivider();
        } else {
            throw new CommandNotFoundException();
        }
    }

    /**
     * Represents the function that will print the exit message.
     */
    protected static void printExit() {
        printDivider();
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    /**
     * Represents the function that will mark the task as done as according to the task class.
     * Task to be marked has to be given by the user in an integer that is within the size of the list of tasks.
     *
     * @param input Input string that is provided by the user.
     */
    protected static void markTask(String input)  {
        int taskNumber = Integer.parseInt(input.substring(4).trim()) - 1;
        tasks.get(taskNumber).markDone();
    }

    /**
     * Represents the function that will mark the task as not done according to the task class.
     * Task to be unmarked has to be given by the user in an integer that is within the size of the list of tasks
     *
     * @param input Input string that is provided by the user.
     */
    protected static void unmarkTask(String input)  {
        int taskNumber = Integer.parseInt(input.substring(6).trim()) - 1;
        tasks.get(taskNumber).umarkDone();
    }

    /**
     * Represents the function that will print the tasks within the list.
     */
    protected static void printList() {
        printDivider();
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task output : tasks) {
            System.out.println(count + "." + output);
            count++;
        }
        printDivider();
    }

    /**
     * Represents the function that will create as new to do as according to the task class.
     * It will also inform users that the task has been successfully added.
     *
     * @param input Input string that is provided by the user.
     */
    protected static void createTodo(String input) {
        tasks.add(new Todo(input.substring(4).trim()));
        System.out.println("Got it. I've added this tasks:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Represents the function that will create the task of type deadline.
     * It requires the user to input both the description and date of the deadline.
     * It will also inform users that the deadline task has been successfully added.
     *
     * @param input Input string that is provided by the user.
     * @throws InvalidInputException if either the deadline date or description is not found.
     */
    protected static void createDeadline(String input) throws InvalidInputException {
        int byIndex = input.indexOf("/by");
        String description = input.substring(8, byIndex).trim();
        String date = input.substring(byIndex + 3).trim();
        if (description.length() < 1 | date.length() < 1) {
            throw new InvalidInputException();
        }
        tasks.add(new Deadline(description, date));
        System.out.println("Got it. I've added this tasks:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Represents the function that will create the task of type event.
     * It requires the user to input the description, event start date and event end date.
     * It will also inform users that the event task has been successfully added.
     *
     * @param input Input string that is provided by the user.
     * @throws IllegalFormatException if the order of from has been placed after the to
     * @throws InvalidInputException if either the expected description or event start or event end date is not found.
     */
    protected static void createEvent(String input) throws IllegalFormatException, InvalidInputException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        String description = input.substring(5, fromIndex).trim();
        String eventStart = input.substring(fromIndex + 5, toIndex).trim();
        String eventEnd = input.substring(toIndex + 3).trim();
        if (description.length() < 1 | eventStart.length() < 1 | eventEnd.length() < 1) {
            throw new InvalidInputException();
        } else if (fromIndex > toIndex) {
            throw new IllegalFormatException();
        }
        tasks.add(new Event(description, eventStart, eventEnd));
        System.out.println("Got it. I've added this tasks:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Represents the function that will remove the task as specified within the list.
     * It requires the user to input the task number that is within the size of the list of task.
     * It will also inform users that the task has been successfully removed.
     *
     * @param input Input string that is provided by the user.
     */
    protected static void deleteTask(String input) {
        int deleteIndex = Integer.parseInt(input.substring(6).trim());
        System.out.println("Noted. I've removed this task:\n" + tasks.get(deleteIndex - 1));
        tasks.remove(tasks.get(deleteIndex - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Represents the function that will help the user search the task that contains the specific keyword
     * It will print out the filtered list of task that only contains that specific keyword.
     *
     * @param input Input string that is provided by the user.
     * @throws IllegalFormatException if the task that has to been searched has not been specified by the user.
     * @throws InvalidInputException if there is not task left within the list.
     */
    protected static void findTask(String input) throws IllegalFormatException, InvalidInputException{
        String keyword = input.substring(4).trim();
        if (keyword.equals("")) {
            throw new IllegalFormatException();
        }
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (Task task : tasks) {
            if (task.findMatch(keyword)) {
                System.out.println(count + "." + task);
                count++;
            }
        }
        if (count == 1) {
            throw new InvalidInputException();
        }
    }

    public static void main(String[] args) {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + LOGO);
        printDivider();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        printDivider();

        while(proceedToNextCommand) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            try {
                executeCommand(input);
            } catch (CommandNotFoundException e) {
                printDivider();
                System.out.println("I'm sorry, but I don't know what that means =(");
                printDivider();
            }
        }
    }
}
