package duke;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidIndexException;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Deals with making sense of the user's command
 */
public class Parser {
    private static Ui ui;

    public Parser() {
        ui = new Ui();
    }

    private static boolean isValidMarkCommand(String response) {
        return response.length() >= 5 && response.substring(0, 5).equalsIgnoreCase("mark ");
    }

    private static boolean isValidUnmarkCommand(String response) {
        return response.length() >= 7 && response.substring(0, 7).equalsIgnoreCase("unmark ");
    }

    private static boolean isValidDeleteCommand(String response) {
        return response.length() >= 7 && response.substring(0, 7).equalsIgnoreCase("delete ");
    }

    private static boolean isValidFindCommand(String response) {
        return response.length() >= 5 && response.substring(0, 5).equalsIgnoreCase("find ");
    }

    private static int parseIndex(String indexString, ArrayList<Task> tasks) throws InvalidIndexException {
        int indexInt;

        indexInt = Integer.parseInt(indexString);
        indexInt -= 1; // convert to 0-index
        if (indexInt >= tasks.size() || indexInt < 0) {
            throw new InvalidIndexException();
        }
        return indexInt;
    }

    private static void parseAddCommand(String sentence, TaskList taskList) throws InvalidCommandException {
        String[] words = sentence.split(" ", 2); // split sentence only on first occurrence of space
        String taskType = words[0].toLowerCase();

        switch (taskType) {
        case "todo":
            try {
                taskList.addToDo(words[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printTodoEmptyDescription();
                return;
            }
            break;
        case "deadline":
            try {
                taskList.addDeadline(words[1]);
            } catch (InvalidCommandException e) {
                ui.printInvalidDeadline();
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printDeadlineEmptyDescription();
                return;
            }
            break;
        case "event":
            try {
                taskList.addEvent(words[1]);
            } catch (InvalidCommandException e) {
                ui.printInvalidEvent();
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printEventEmptyDescription();
                return;
            }
            break;
        default:
            throw new InvalidCommandException();
        }

        ui.printSuccessfulAddMessage(taskList.getLatestTask(), taskList.getNumTasks()); // get latest task in taskList
    }

    private static void parseMarkCommand(String indexString, TaskList taskList) {
        int indexInt;
        try {
            indexInt = parseIndex(indexString, taskList.getTasks());
            Task currTask = taskList.getTasks().get(indexInt);
            currTask.setIsComplete(true);
            ui.printSuccessfulMark(currTask);
        } catch (NumberFormatException e) {
            ui.printIndexNotNumber();
        } catch (InvalidIndexException e) {
            ui.printInvalidIndex();
        }
    }

    private static void parseUnmarkTask(String indexString, TaskList taskList) {
        int indexInt;

        try {
            indexInt = parseIndex(indexString, taskList.getTasks());
            Task currTask = taskList.getTasks().get(indexInt);
            currTask.setIsComplete(false);
            ui.printSuccessfulUnmark(currTask);
        } catch (NumberFormatException e) {
            ui.printIndexNotNumber();
        } catch (InvalidIndexException e) {
            ui.printInvalidIndex();
        }
    }

    private static void parseDeleteTask(String indexString, TaskList taskList) {
        int indexInt;

        try {
            indexInt = parseIndex(indexString, taskList.getTasks());
            Task deletedTask = taskList.deleteTask(indexInt);
            ui.printSuccessfulDelete(deletedTask, taskList.getNumTasks());
        } catch (NumberFormatException e) {
            ui.printIndexNotNumber();
        } catch (InvalidIndexException e) {
            ui.printInvalidIndex();
        }
    }

    private static void parseFindTasks(String filterString, TaskList taskList) {
        ArrayList<Task> filteredList = taskList.filterTaskList(filterString);
        if (filteredList.size() > 0) {
            ui.printFilteredList(filteredList);
            return;
        }
        ui.printFilteredListEmpty();
    }


    /**
     * Checks the user's input and applies the corresponding command if it is a valid command.
     *
     * @param userInput The input string supplied by the user through the standard input.
     * @param taskList  The list of <code>Todo</code>, <code>Event</code> and <code>Deadline</code>
     *                  <code>Task</code>s.
     */
    public static void parseResponse(String userInput, TaskList taskList) {
        if (userInput.equalsIgnoreCase("list")) {
            ui.printSeparator();
            ui.displayList(taskList.getTasks());
            ui.printSeparator();
        } else if (isValidMarkCommand(userInput)) {
            ui.printSeparator();
            parseMarkCommand(userInput.substring(5), taskList);
            ui.printSeparator();
        } else if (isValidUnmarkCommand(userInput)) {
            ui.printSeparator();
            parseUnmarkTask(userInput.substring(7), taskList);
            ui.printSeparator();
        } else if (isValidDeleteCommand(userInput)) {
            ui.printSeparator();
            parseDeleteTask(userInput.substring(7), taskList);
            ui.printSeparator();
        } else if (isValidFindCommand(userInput)) {
            ui.printSeparator();
            parseFindTasks(userInput.substring(5), taskList);
            ui.printSeparator();
        } else {
            ui.printSeparator();
            try {
                parseAddCommand(userInput, taskList);
            } catch (InvalidCommandException e) {
                ui.printInvalidCommandException();
            }
            ui.printSeparator();
        }
    }
}
