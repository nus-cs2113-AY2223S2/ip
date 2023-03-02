package duke;
import duke.addable.*;
import duke.exception.*;
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
            }
            currentInput = in.nextLine();
        }
    }
    // COMMAND HANDLERS

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
    public static void list() {
        ui.printMessage(ui.getFormattedList(taskList));
    }

    public static int getTaskIndex(String index) throws MarkNonexistentTaskException {
        int taskIndex = Integer.parseInt(index) - 1;
        if (taskIndex > taskList.getLength() - 1 || taskIndex < 0) {
            throw new MarkNonexistentTaskException(taskIndex + 1);
        }
        return taskIndex;
    }
    public static void mark(String[] parameters) throws MarkNonexistentTaskException {
        int taskIndex = getTaskIndex(parameters[0]);
        taskList.get(taskIndex).setDone(true);
        String[] message = {
                "Cool! I've marked this task as done:",
                taskList.get(taskIndex).toString()
        };
        ui.printMessage(message);
    }

    public static void unmark(String[] parameters) throws MarkNonexistentTaskException {
        int taskIndex = getTaskIndex(parameters[0]);
        taskList.get(taskIndex).setDone(false);
        String[] message = {
                "Ok, I've marked this task as not done yet:",
                taskList.get(taskIndex).toString()
        };
        ui.printMessage(message);
    }
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
    public static Deadline getNewDeadline(String taskDescription, String[] inputSections) throws ArgumentBlankException {
        String date = inputSections[1].replaceFirst("by", "");
        return new Deadline(
                taskDescription,
                date,
                false
        );
    }
    public static Event getNewEvent(String taskDescription, String[] inputSections) throws ArgumentBlankException {
        return new Event(
                taskDescription,
                inputSections[1].replaceFirst("from", ""),
                inputSections[2].replaceFirst("to", ""),
                false
        );
    }
    public static ToDo getNewTodo(String taskDescription) throws ArgumentBlankException {
        return new ToDo(taskDescription, false);
    }
}
