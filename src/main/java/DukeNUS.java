import io.DukeNUSPrinter;
import io.TasksDataRead;
import io.TasksDataWrite;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toCollection;

public class DukeNUS {
    private static final String FILEPATH = "data/dukeNUS.txt";
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static ArrayList<Task> getFoundTasks(ArrayList<Task> tasks, String searchPrompt) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(searchPrompt))
                .collect(toCollection(ArrayList::new));
    }

    /**
     * @param todo A newly constructed todo object as a child of Task that has a user-defined description.
     */
    public static void addTodo(Todo todo) {
        tasks.add(todo);
        DukeNUSPrinter.printAddedTask(todo.getTaskString(), tasks.size());
    }

    /**
     * @param deadline A newly constructed deadline object as a child of Task that has a user-defined description
     *                 and due date.
     */
    public static void addDeadline(Deadline deadline) {
        tasks.add(deadline);
        DukeNUSPrinter.printAddedTask(deadline.getTaskString(), tasks.size());
    }

    /**
     * @param event A newly constructed event object as a child of Task that has a user-defined description,
     *              from date, and to date.
     */
    public static void addEvent(Event event) {
        tasks.add(event);
        DukeNUSPrinter.printAddedTask(event.getTaskString(), tasks.size());
    }

    /**
     * @param taskIndex The 1-based index of the task the user is referring to. Will remove the task from the array.
     */
    private static void deleteTask(int taskIndex) {
        DukeNUSPrinter.printDeletedTask(tasks.get(taskIndex - 1).getTaskString());
        tasks.remove(taskIndex - 1);
    }
    /**
     * @param taskIndex The 1-based index of the task the user is referring to. Will cause the isDone property of the
     *                  task to be true.
     */
    public static void markTask(int taskIndex) {
        tasks.get(taskIndex - 1).setDone(true);
        DukeNUSPrinter.printMarkedTask(tasks.get(taskIndex - 1).getTaskString());
    }

    /**
     * @param taskIndex The 1-based index of the task the user is referring to. Will cause the isDone property of the
     *                  task to be false.
     */
    public static void unmarkTask(int taskIndex) {
        tasks.get(taskIndex - 1).setDone(false);
        DukeNUSPrinter.printUnmarkedTask(tasks.get(taskIndex - 1).getTaskString());
    }

    /**
     * Directs the input to execute the correct command.
     *
     * @param userInputWords The complete sentence the user inputted, often consisting of the command as the first word
     *                       and its arguments as its subsequent words.
     */
    private static void interpretCommand(String[] userInputWords) {
        switch (userInputWords[0]) {
        case "list":
            if (tasks.isEmpty()) {
                DukeNUSPrinter.printMessage("You have no tasks. ¯\\_(ツ)_/¯");
            } else {
                DukeNUSPrinter.listTasks(tasks);
            }
            break;
        case "find":
            ArrayList<Task> filteredTasks = getFoundTasks(tasks, userInputWords[1]);
            if (filteredTasks.isEmpty()) {
                DukeNUSPrinter.printMessage("Tasks containing the term \"" + userInputWords[1] + "\" were not found.");
            } else {
                DukeNUSPrinter.showSearchResults(filteredTasks);
            }
            break;
        case "delete":
            try {
                deleteTask(parseInt(userInputWords[1]));
            } catch (IndexOutOfBoundsException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The index of the task is missing or out of bounds.");
            } catch (NumberFormatException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The task index you have entered is not a number.");
            } catch (NullPointerException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The task index you have provided returns a null task.");
            }
            break;
        case "mark":
            try {
                markTask(parseInt(userInputWords[1]));
            } catch (IndexOutOfBoundsException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The index of the task is missing or out of bounds.");
            } catch (NumberFormatException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The task index you have entered is not a number.");
            } catch (NullPointerException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The task index you have provided returns a null task.");
            }
            break;
        case "unmark":
            try {
                unmarkTask(parseInt(userInputWords[1]));
            } catch (ArrayIndexOutOfBoundsException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The index of the task is missing or out of bounds.");
            } catch (NumberFormatException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The task index you have entered is not a number.");
            } catch (NullPointerException exception) {
                DukeNUSPrinter.printMessage("☹ Error: The task index you have provided returns a null task.");
            }
            break;
        case "todo":
            try {
                addTodo(new Todo(userInputWords[1], false));
            } catch (ArrayIndexOutOfBoundsException exception) {
                DukeNUSPrinter.printMessage("☹ Error: please specify the description of the todo.");
            }
            break;
        case "deadline":
            try {
                String[] deadlineInput = userInputWords[1].split("/by", 2);
                addDeadline(new Deadline(deadlineInput[0], deadlineInput[1], false));
            } catch (ArrayIndexOutOfBoundsException exception) {
                DukeNUSPrinter.printMessage("☹ Error: incorrect syntax. Correct usage: " +
                        "`deadline [description] /by [deadline]`.");
            } catch (DateTimeParseException exception) {
                DukeNUSPrinter.printMessage("☹ Error: invalid date format. Try the yyyy-MM-dd format (eg. 2000-12-31)");
            }
            break;
        case "event":
            try {
                String[] eventInput = userInputWords[1].split("/from|/to", 3);
                addEvent(new Event(eventInput[0], eventInput[1], eventInput[2], false));
            } catch (ArrayIndexOutOfBoundsException exception) {
                DukeNUSPrinter.printMessage("☹ Error: incorrect syntax. Correct usage: " +
                        "`event [description] /from [start_date] /to [end_date]`.");
            } catch (DateTimeParseException exception) {
                DukeNUSPrinter.printMessage("☹ Error: invalid date format. Try the yyyy-MM-dd format (eg. 2000-12-31)");
            }
            break;
        default:
            DukeNUSPrinter.printMessage("☹ Error: Unknown command. Please try again.");
            break;
        }
    }

    public static void main(String[] args) {
        try {
            tasks = TasksDataRead.readSavedTasks(FILEPATH);
        } catch (FileNotFoundException | NoSuchElementException exception) {
            //There is no data to read. Continue.
        }
        Scanner scanner = new Scanner(System.in);
        DukeNUSPrinter.printWelcomeMessage();
        String userInput = scanner.nextLine();
        String[] userInputWords = userInput.split(" ", 2); //Contains 2 strings from the user delimited by spaces. The first word is the command and the subsequent words are arguments.
        //Continue reading the first word until "bye" is said.
        while (!Objects.equals(userInput, "bye")) {
            interpretCommand(userInputWords);
            userInput = scanner.nextLine();
            userInputWords = userInput.split(" ", 2);
        }
        DukeNUSPrinter.printFarewellMessage();
        try {
            TasksDataWrite.writeSavedTasks(FILEPATH, tasks);
        } catch (IOException e) {
            DukeNUSPrinter.printMessage("☹ Error: Something went wrong when saving your tasks to " + FILEPATH);
        }
    }
}


