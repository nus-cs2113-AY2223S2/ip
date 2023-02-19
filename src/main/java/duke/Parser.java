package duke;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidIndexException;
import duke.tasks.Task;

import java.util.ArrayList;

public class Parser implements java.io.Serializable {
    private static Ui ui;

    public Parser() {
        ui = new Ui();
    }

    private static boolean isValidMarkCommand(String response) {
        return response.length() >= 5 && response.substring(0, 5).equals("mark ");
    }

    private static boolean isValidUnmarkCommand(String response) {
        return response.length() >= 7 && response.substring(0, 7).equals("unmark ");
    }

    private static boolean isValidDeleteCommand(String response) {
        return response.length() >= 7 && response.substring(0, 7).equals("delete ");
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

    public static void parseAddCommand(String sentence, TaskList taskList) throws InvalidCommandException {
        String[] words = sentence.split(" ", 2); // split sentence only on first occurrence of space
        String taskType = words[0];

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

        ui.printSuccessfulAddMessage(taskList.getLatestTask(), taskList.getTasks()); // get latest task in taskList
    }

    public static void parseMarkCommand(String indexString, TaskList taskList) {
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

    public static void parseUnmarkTask(String indexString, TaskList taskList) {
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

    public static void parseDeleteTask(String indexString, TaskList taskList) {
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

    public static void parseResponse(String response, TaskList taskList) {
        if (response.equals("list")) {
            ui.printSeparator();
            ui.displayList(taskList.getTasks());
            ui.printSeparator();
        } else if (isValidMarkCommand(response)) {
            ui.printSeparator();
            parseMarkCommand(response.substring(5), taskList);
            ui.printSeparator();
        } else if (isValidUnmarkCommand(response)) {
            ui.printSeparator();
            parseUnmarkTask(response.substring(7), taskList);
            ui.printSeparator();
        } else if (isValidDeleteCommand(response)) {
            ui.printSeparator();
            parseDeleteTask(response.substring(7), taskList);
            ui.printSeparator();
        } else {
            ui.printSeparator();
            try {
                parseAddCommand(response, taskList);
            } catch (InvalidCommandException e) {
                ui.printInvalidCommandException();
            }
            ui.printSeparator();
        }
    }
}
