package duke;

import duke.exceptions.DukeCreateDatabaseException;
import duke.exceptions.DukeLoadDatabaseException;
import duke.exceptions.DukeSaveDatabaseException;
import duke.exceptions.DukeWrongArgsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import java.util.Scanner;

public class Duke {

    public static boolean isCompleted = false;

    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage();
    private static final TaskList tasks = new TaskList();

    /**
     * Checks input from user and executes the appropriate command.
     *
     * @param inputMessage Raw input string from user
     */
    public static void checkInput(String inputMessage) {
        String cleanInput = inputMessage.trim();
        String[] message = cleanInput.split(" ");
        if (message[0].equalsIgnoreCase("bye")) {
            isCompleted = true;
        } else if (message[0].equalsIgnoreCase("list")) {
            displayList();
        } else if (message[0].equalsIgnoreCase("mark")) {
            markItem(message);
        } else if (message[0].equalsIgnoreCase("unmark")) {
            unmarkItem(message);
        } else if (message[0].equalsIgnoreCase("todo")) {
            addTodo(cleanInput);
        } else if (message[0].equalsIgnoreCase("deadline")) {
            addDeadline(message);
        } else if (message[0].equalsIgnoreCase("event")) {
            addEvent(message);
        } else if (message[0].equalsIgnoreCase("delete")) {
            deleteTask(message);
        } else {
            unknownCommand();
        }
    }

    /**
     * Marks the task in the task-list based on the numbering specified by the user. Prints out an appropriate
     * error message whenever it receives an invalid input.
     *
     * @param message Input string from user separated by spaces.
     */
    public static void markItem(String[] message) {
        ui.printSeparator();
        try {
            int itemIndex = tasks.markItem(message);
            String outputMessage = String.format("Nice! I've marked task %d as done:", itemIndex + 1);
            ui.printMessage(outputMessage);
            ui.printMessage(tasks.get(itemIndex).toString());
        } catch (DukeWrongArgsException error) {
            String errorMessage = String.format("Wrong number of arguments. Expected 2, received %d",
                    message.length);
            ui.printMessage(errorMessage);
        } catch (NumberFormatException error) {
            String errorMessage = "Expected a valid number for second argument.";
            String errorMessageEcho = String.format("You entered %s, which is invalid!", message[1]);
            ui.printMessage(errorMessage);
            ui.printMessage(errorMessageEcho);
        } catch (IndexOutOfBoundsException error) {
            String errorMessage = "Out of bounds value provided.";
            String errorMessageEcho = String.format("List only has %d items!", tasks.size());
            ui.printMessage(errorMessage);
            ui.printMessage(errorMessageEcho);
        } finally {
            ui.printSeparator();
        }
    }

    /**
     * Unmarks the task in the task-list based on the numbering specified by the user. Prints out an
     * appropriate error message whenever it receives an invalid input.
     *
     * @param message Input string from user separated by spaces.
     */
    public static void unmarkItem(String[] message) {
        ui.printSeparator();
        try {
            int itemIndex = tasks.unmarkItem(message);
            String outputMessage = String.format("OK, I've marked task %d as not done yet:", itemIndex + 1);
            ui.printMessage(outputMessage);
            ui.printMessage(tasks.get(itemIndex).toString());
        } catch (DukeWrongArgsException error) {
            String errorMessage = String.format("Wrong number of arguments. Expected 2, received %d",
                    message.length);
            ui.printMessage(errorMessage);
        } catch (NumberFormatException error) {
            String errorMessage = "Expected a valid number for second argument.";
            String errorMessageEcho = String.format("You entered %s, which is invalid!", message[1]);
            ui.printMessage(errorMessage);
            ui.printMessage(errorMessageEcho);
        } catch (IndexOutOfBoundsException error) {
            String errorMessage = "Out of bounds value provided.";
            String errorMessageEcho = String.format("List only has %d items!", tasks.size());
            ui.printMessage(errorMessage);
            ui.printMessage(errorMessageEcho);
        } finally {
            ui.printSeparator();
        }
    }

    /**
     * Adds a Todo task to the task-list. Prints an error message if insufficient arguments are provided.
     *
     * @param cleanInput Input string from user that has been trimmed.
     */
    public static void addTodo(String cleanInput) {
        ui.printSeparator();

        // Check if second argument was provided
        try {
            Todo todo = tasks.addTodo(cleanInput);
            ui.printMessage("Got it. I've added this todo:");
            ui.printMessage(String.format(" %s", todo));
            ui.printMessage(String.format("Now you have %d tasks in the list.", tasks.size()));
        } catch (StringIndexOutOfBoundsException error) {
            String errorMessage = "Expected 2 arguments, only 1 provided.";
            ui.printMessage(errorMessage);
        } finally {
            ui.printSeparator();
        }
    }

    /**
     * Adds a Deadline task to the task-list. Prints an error message if insufficient arguments are provided.
     *
     * @param message Input string from user separated by spaces.
     */
    public static void addDeadline(String[] message) {
        ui.printSeparator();

        // Check if task and deadline given
        try {
            Deadline deadline = tasks.addDeadline(message);
            ui.printMessage("Got it. I've added this deadline:");
            ui.printMessage(String.format(" %s", deadline));
            ui.printMessage(String.format("Now you have %d tasks in the list.", tasks.size()));
        } catch (DukeWrongArgsException error) {
            String errorMessage = "Command to enter new deadline entered wrongly.";
            String errorMessageExample = "Example command: \"deadline <task> /by <endDate>\"";
            ui.printMessage(errorMessage);
            ui.printMessage(errorMessageExample);
        } finally {
            ui.printSeparator();
        }
    }

    /**
     * Adds a Deadline task to the task-list. Prints an error message if insufficient arguments are provided.
     *
     * @param message Input string from user separated by spaces.
     */
    public static void addEvent(String[] message) {
        ui.printSeparator();

        // Check if task, start and end date given
        try {
            Event event = tasks.addEvent(message);
            ui.printMessage("Got it. I've added this event:");
            ui.printMessage(String.format(" %s", event));
            ui.printMessage(String.format("Now you have %d tasks in the list.", tasks.size()));
        } catch (DukeWrongArgsException error) {
            String errorMessage = "Command to enter new event entered wrongly.";
            String errorMessageExample =
                    "Example command: \"event <task> /from <startDate> /to " + "<endDate>\"";
            ui.printMessage(errorMessage);
            ui.printMessage(errorMessageExample);
        } finally {
            ui.printSeparator();
        }
    }

    /**
     * Displays all the tasks currently in the task-list.
     */
    public static void displayList() {
        ui.printSeparator();
        int numItems = tasks.size();
        if (numItems == 0) {
            ui.printMessage("List is empty!");
            ui.printSeparator();
            return;
        }

        ui.printMessage("Here are the tasks in your list:");
        for (int i = 0; i < numItems; i++) {
            String item = tasks.get(i).toString();
            String outputMessage = String.format("%d.%s", i + 1, item);
            ui.printMessage(outputMessage);
        }
        ui.printSeparator();
    }

    /**
     * Deletes a task from the task-list. Prints an appropriate error message when command is called by user
     * incorrectly.
     *
     * @param message Input string from user separated by spaces.
     */
    public static void deleteTask(String[] message) {
        ui.printSeparator();
        try {
            Task deletedTask = tasks.deleteTask(message);
            String outputMessage = "Noted. I've removed this task:";
            String outputRemaining = String.format("Now you have %d tasks in the list.", tasks.size());
            ui.printMessage(outputMessage);
            ui.printMessage(deletedTask.toString());
            ui.printMessage(outputRemaining);
        } catch (DukeWrongArgsException error) {
            String errorMessage = String.format("Wrong number of arguments. Expected 2, received %d",
                    message.length);
            ui.printMessage(errorMessage);
        } catch (NumberFormatException error) {
            String errorMessage = "Expected a valid number for second argument.";
            String errorMessageEcho = String.format("You entered %s, which is invalid!", message[1]);
            ui.printMessage(errorMessage);
            ui.printMessage(errorMessageEcho);
        } catch (IndexOutOfBoundsException error) {
            String errorMessage = "Out of bounds value provided.";
            String errorMessageEcho = String.format("List only has %d items!", tasks.size());
            ui.printMessage(errorMessage);
            ui.printMessage(errorMessageEcho);
        } finally {
            ui.printSeparator();
        }
    }

    /**
     * Greets user and loads previously created database file. If past database file does not exist, new
     * database file will be created.
     */
    public static void startDuke() {
        ui.printSeparator();
        ui.printMessage("Hello! I'm Duke");
        ui.printMessage("Let me check the current list of tasks");

        try {
            String fileContent = storage.loadDatabase();

            if (!fileContent.isEmpty()) {
                ui.printMessage("Loading previous task list...");
                tasks.loadTaskList(fileContent);
                ui.printLoadedTaskList(tasks);
            } else {
                ui.printMessage("Database file does not exist, creating one now!");
            }
        } catch (DukeCreateDatabaseException error) {
            ui.printMessage("Unable to create database file!");
        } catch (DukeLoadDatabaseException error) {
            ui.printMessage("Unable to load database file!");
        } finally {
            ui.printSeparator();
        }
    }

    /**
     * Saves current task-list into database and then exits the program.
     */
    public static void endDuke() {
        ui.printSeparator();
        ui.printMessage("Saving current list of data into database...");

        try {
            storage.saveDatabase(tasks);
            ui.printMessage("Done saving list of tasks.");
        } catch (DukeSaveDatabaseException error) {
            ui.printMessage("Unable to save database file!");
        } finally {
            ui.printMessage("Bye. Hope to see you again soon!");
            ui.printSeparator();
        }
    }

    private static void unknownCommand() {
        ui.printSeparator();
        ui.printMessage("Unknown command entered, please enter a proper command!");
        ui.printSeparator();
    }

    public static void main(String[] args) {
        startDuke();

        String line;
        Scanner in = new Scanner(System.in);

        do {
            line = in.nextLine();
            checkInput(line);
        } while (!isCompleted);

        endDuke();
    }
}
