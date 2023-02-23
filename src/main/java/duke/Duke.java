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

    public static void checkInput(String inputMessage) {
        String cleanInput = inputMessage.trim();
        String[] message = cleanInput.split(" ");
        if (message[0].equalsIgnoreCase("bye")) {
            isCompleted = true;
        } else if (message[0].equalsIgnoreCase("list")) {
            displayList(tasks);
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
        } else if (message[0].equalsIgnoreCase("find")) {
            findTask(cleanInput);
        } else {
            unknownCommand();
        }
    }

    public static void markItem(String[] message) {
        ui.printSeparator();
        try {
            int itemIndex = tasks.markItem(message);
            String outputMessage = String.format("Nice! I've marked task %d as done:", itemIndex+1);
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
            String errorMessageEcho = String.format("List only has %d items!",
                    tasks.size());
            ui.printMessage(errorMessage);
            ui.printMessage(errorMessageEcho);
        } finally {
            ui.printSeparator();
        }
    }

    public static void unmarkItem(String[] message) {
        ui.printSeparator();
        try {
            int itemIndex = tasks.unmarkItem(message);
            String outputMessage = String.format("OK, I've marked task %d as not done yet:", itemIndex+1);
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
            String errorMessageEcho = String.format("List only has %d items!",
                    tasks.size());
            ui.printMessage(errorMessage);
            ui.printMessage(errorMessageEcho);
        } finally {
            ui.printSeparator();
        }
    }

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

    public static void displayList(TaskList tasks) {
        ui.printSeparator();
        ui.printTaskList(tasks);
        ui.printSeparator();
    }

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
            String errorMessageEcho = String.format("List only has %d items!",
                    tasks.size());
            ui.printMessage(errorMessage);
            ui.printMessage(errorMessageEcho);
        } finally {
            ui.printSeparator();
        }
    }

    public static void findTask(String message) {
        ui.printSeparator();
        try {
            TaskList tasksFound = tasks.findTask(message);
            if (tasksFound.isEmpty()) {
                ui.printMessage("No tasks found!");
            } else {
                ui.printMessage("Here are the matching tasks in your list:");
                ui.printTaskList(tasksFound);
            }
        } catch (StringIndexOutOfBoundsException error) {
            String errorMessage = "Expected 2 arguments, only 1 provided.";
            ui.printMessage(errorMessage);
        } finally {
            ui.printSeparator();
        }
    }

    public static void startDuke(){
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
