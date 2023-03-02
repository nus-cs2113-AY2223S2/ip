package duke;
import duke.addable.*;
import duke.exception.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
public class Duke {
    private static TaskList taskList = new TaskList();
    private static Ui ui = new Ui();
    private static Storage storage = new Storage(ui);

    public static void main(String[] args) {
        taskList.setTasks(storage.getTasks());
        ui.printIntro();
        list();
        mainLoop();
        ui.printExit();
    }

    /**
     * Main loop for program, gets input, parses input, and executes user's commands
     * @return nothing
     */
    public static void mainLoop() {
        Scanner in = new Scanner(System.in);
        Parser parser = new Parser();
        String currentInput = in.nextLine();
        Command command = null;
        String[] parameters = null;
        while (command != Command.BYE) {
            try {
                parameters = parser.getParameters(currentInput);
                command = parser.getCommand(currentInput);
                switch (command) {
                case TODO:
                case DEADLINE:
                case EVENT:
                    handleAddTask(currentInput, command);
                    break;
                case DELETE:
                    delete(parameters);
                    break;
                case LIST:
                    list();
                    break;
                case MARK:
                    mark(parameters);
                    break;
                case UNMARK:
                    unmark(parameters);
                    break;
                case FIND:
                    find(parameters);
                    break;
                case BYE:
                    return;
                default:
                    throw new UnknownCommandException("Blank");
                }
                storage.saveCurrentTaskList();
            } catch (MarkNonexistentTaskException e) {
                ui.printInvalidInputMessage("task " + e.taskIndex + " does not currently exist.");
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printInvalidInputMessage("Unknown command");
            } catch (UnknownCommandException e) {
                ui.printInvalidInputMessage("Unknown command \'" + e.unknownCommand + "\'");
            } catch (NumberFormatException e) {
                ui.printInvalidInputMessage("Argument for mark/unmark/delete must be an integer");
            } catch (DateTimeParseException e) {
                ui.printInvalidInputMessage("Invalid format for date and/or time");
            }
            currentInput = in.nextLine();
        }
    }

    // COMMAND HANDLERS
    /**
     * Handles "find" command: finds and prints all tasks in list that contain a certain substring
     * @param parameters Parameters passed by user
     * @return nothing
     */
    public static void find(String[] parameters) {
        String toFind = String.join(" ", parameters);
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getDescription().contains(toFind)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.isEmpty()) {
            ui.printMessage("No matching tasks found");
        } else {
            ui.printMessage(ui.getFormattedList("Here are the matching tasks in your list:", foundTasks));
        }
    }
    /**
     * Handles "delete" command: deletes task at a certain index in list
     * @param parameters Parameters passed by user
     * @return nothing
     * @throws MarkNonexistentTaskException If user tries to delete a task at an index which is out of range of the list
     */
    public static void delete(String[] parameters) throws MarkNonexistentTaskException {
        int taskIndex = getTaskIndex(parameters[0]);
        String[] taskDeletedMessage = {
                "Noted. I've removed this task:",
                taskList.get(taskIndex).toString(),
                "Now you have " + (taskList.getLength() - 1) + " tasks in the list."
        };
        taskList.remove(taskIndex);
        ui.printMessage(taskDeletedMessage);
    }
    /**
     * Handles "list" command: lists all tasks in list in desired format
     */
    public static void list() {
        ui.printMessage(ui.getFormattedList("Here are the tasks in your list:", taskList.getTasks()));
    }

    /**
     * Gets index as integer from index as string and checks if index is in range
     * @param index String of index to parse
     * @return index as integer
     * @throws MarkNonexistentTaskException If user tries to access a task at an index which is out of range of the list
     */
    public static int getTaskIndex(String index) throws MarkNonexistentTaskException {
        int taskIndex = Integer.parseInt(index) - 1;
        if (taskIndex > taskList.getLength() - 1 || taskIndex < 0) {
            throw new MarkNonexistentTaskException(taskIndex + 1);
        }
        return taskIndex;
    }

    /**
     * Handles "mark" command: marks task at a certain index in list as done and prints message accordingly
     * @param parameters Parameters passed by user
     * @return nothing
     * @throws MarkNonexistentTaskException If user tries to mark a task at an index which is out of range of the list
     */
    public static void mark(String[] parameters) throws MarkNonexistentTaskException {
        int taskIndex = getTaskIndex(parameters[0]);
        taskList.get(taskIndex).setDone(true);
        String[] message = {
                "Cool! I've marked this task as done:",
                taskList.get(taskIndex).toString()
        };
        ui.printMessage(message);
    }

    /**
     * Handles "unmark" command: unmarks task at a certain index in list as done and prints message accordingly
     * @param parameters Parameters passed by user
     * @return nothing
     * @throws MarkNonexistentTaskException If user tries to unmark a task at an index which is out of range of the list
     */
    public static void unmark(String[] parameters) throws MarkNonexistentTaskException {
        int taskIndex = getTaskIndex(parameters[0]);
        taskList.get(taskIndex).setDone(false);
        String[] message = {
                "Ok, I've marked this task as not done yet:",
                taskList.get(taskIndex).toString()
        };
        ui.printMessage(message);
    }

    /**
     * Handles "todo", "deadline", and "event" command: adds task to list and prints message accordingly
     * @param input Words input by user
     * @param command enum indicating which command was input by user
     * @return nothing
     */
    public static void handleAddTask(String input, Command command) {
        Task addedTask = null;
        try {
            addedTask = addTask(input, command);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printInvalidInputMessage();
        } catch (ArgumentBlankException e) {
            ui.printInvalidInputMessage(
                    "Argument \'" + e.argumentType + "\' cannot be blank for command \'" +
                            e.commandType + "\'"
                    );
        } catch (UnknownCommandException e) {
            ui.printInvalidInputMessage("Unknown command \'" + e.unknownCommand + "\'");
        }
        if (addedTask == null) {
            return;
        }
        ui.printAddTaskMessage(addedTask, taskList.getLength());
    }
    /**
     * Creates and returns task to add based on user input
     * @param input Words input by user
     * @param command enum indicating which command was input by user
     * @return Task to add
     * @throws ArgumentBlankException If one of the required arguments for a command is not provided
     * @throws UnknownCommandException If the command provided is not one of the accepted commands
     */
    public static Task addTask(String input, Command command) throws ArgumentBlankException, UnknownCommandException {
        String[] inputSections = input.split("/");
        String[] firstSectionArguments = inputSections[0].split(" ", 2);
        if (firstSectionArguments.length < 2) {
            throw new ArgumentBlankException(command.toString(), "description");
        }
        String taskDescription = firstSectionArguments[1];

        Task taskToAdd;

        switch (command) {
        case DEADLINE:
            taskToAdd = getNewDeadline(taskDescription, inputSections);
            break;
        case EVENT:
            taskToAdd = getNewEvent(taskDescription, inputSections);
            break;
        case TODO:
            taskToAdd = getNewTodo(taskDescription);
            break;
        default:
            throw new UnknownCommandException(command.toString());
        }
        taskList.add(taskToAdd);
        return taskToAdd;
    }
    /**
     * Creates and returns new Deadline task
     * @param taskDescription Description of deadline
     * @param inputSections Sections of user's input
     * @return new Deadline task
     * @throws ArgumentBlankException If one of the required arguments for a deadline is not provided (e.g. due date)
     */
    public static Deadline getNewDeadline(String taskDescription, String[] inputSections) throws ArgumentBlankException {
        String date = inputSections[1].replaceFirst("by", "");
        return new Deadline(
                taskDescription,
                date,
                false
        );
    }

    /**
     * Creates and returns new Event task
     * @param taskDescription Description of event
     * @param inputSections Sections of user's input
     * @return new Event task
     * @throws ArgumentBlankException If one of the required arguments for an event is not provided (e.g. start time)
     */
    public static Event getNewEvent(String taskDescription, String[] inputSections) throws ArgumentBlankException {
        return new Event(
                taskDescription,
                inputSections[1].replaceFirst("from", ""),
                inputSections[2].replaceFirst("to", ""),
                false
        );
    }

    /**
     * Creates and returns new Todo task
     * @param taskDescription Description of deadline
     * @return new Todo
     * @throws ArgumentBlankException If one of the required arguments for a todo is not provided (e.g. description)
     */
    public static ToDo getNewTodo(String taskDescription) throws ArgumentBlankException {
        return new ToDo(taskDescription, false);
    }
}
