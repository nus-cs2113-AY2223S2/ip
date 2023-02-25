package duke;

import duke.exceptions.EmptyInputException;
import duke.exceptions.IllegalInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.database.Storage;

import java.io.IOException;

public class Parser {

    /** Marks task as done or undone and updates database */
    public static void markDoneOrUndone(String command, TaskList myList, Storage database) {
        try {
            String[] words = command.split(" ");
            String firstWord = words[0];
            String taskToMarkOrUnmarkString = command.substring(command.length() - 1);
            int taskToMarkOrUnmark = Integer.parseInt(taskToMarkOrUnmarkString) - 1;
            Ui.printMarkOrUnmarkMessage(myList, firstWord, taskToMarkOrUnmark);
            String stringToAdd = "";
            for (Task task : myList.tasks) {
                stringToAdd += database.stringToWrite(task).toString() + System.lineSeparator();
            }
            database.writeToFile(stringToAdd);
        } catch (IndexOutOfBoundsException e) {
            Ui.printIllegalIndexMessage();
        } catch (IOException e) {
            Ui.printInitialiseErrorMessage();
        }
    }

    /**
     * Adds the input text and makes the task as a todo type and updates database
     *
     * @param currTask
     * @param myList
     */
    public static void makeToDoFunction(String currTask, TaskList myList) throws EmptyInputException {
        String[] newTask = currTask.split(" ", 2);
        if (newTask[1].isBlank()) {
            throw new EmptyInputException();
        }
        Task toDoTask = new ToDo(newTask[1]);
        myList.addToTaskList(toDoTask);
        Ui.printTaskMessage(myList);
        Storage.addTaskToDatabase(toDoTask);
    }

    /**
     * Adds the input text and makes the task as a Deadline type and updates database
     *
     * @param currTask
     * @param myList
     */
    public static void makeDeadlinesFunction(String currTask, TaskList myList)
            throws EmptyInputException, IllegalInputException {
        if (currTask.contains("/by")) {
            String[] newTask = currTask.split(" ", 2);
            if (newTask[1].isBlank()) {
                throw new EmptyInputException();
            }
            String[] split = newTask[1].split(" /by ");
            if (split[1].isBlank()) {
                throw new EmptyInputException();
            }
            Task deadlineTask = new Deadline(split[0], split[1]);
            myList.addToTaskList(deadlineTask);
            Ui.printTaskMessage(myList);
            Storage.addTaskToDatabase(deadlineTask);
        } else {
            throw new IllegalInputException();
        }
    }

    /**
     * Adds the input text and makes task as an Event type and updates database
     *
     * @param currTask
     * @param myList
     */
    public static void makeEventFunction(String currTask, TaskList myList)
            throws EmptyInputException, IllegalInputException {
        if (currTask.contains("/from") && currTask.contains("/to")) {
            String[] newTask = currTask.split(" ", 2);
            if (newTask[1].isBlank()) {
                throw new EmptyInputException();
            }
            String[] split = newTask[1].split(" /");
            String timeFrom = split[1].substring(5);
            String timeTo = split[2].substring(3);
            Task eventTask = new Event(split[0], timeFrom, timeTo);
            myList.addToTaskList(eventTask);
            Ui.printTaskMessage(myList);
            Storage.addTaskToDatabase(eventTask);
        } else {
            throw new IllegalInputException();
        }
    }

    public static void handleMarkUnmark(String currTask, TaskList myList, Storage database) {
        try {
            checkMarkUnmark(currTask);
            markDoneOrUndone(currTask, myList, database);
        } catch (EmptyInputException e) {
            Ui.printEmptyInputMessage(currTask);
        } catch (NumberFormatException e) {
            Ui.printIllegalInputMessage();
        }
    }

    public static void handleToDo(String currTask, TaskList myList) {
        try {
            makeToDoFunction(currTask, myList);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printEmptyInputMessage(currTask);
        } catch (EmptyInputException e) {
            Ui.printEmptyInputMessage(currTask.trim());
        }
    }

    public static void handleDeadline(String currTask, TaskList myList) {
        try {
            makeDeadlinesFunction(currTask, myList);
        } catch (EmptyInputException e) {
            Ui.printEmptyInputMessage(currTask.trim());
        } catch (IllegalInputException e) {
            Ui.printIllegalInputMessage();
        }
    }

    public static void handleEvent(String currTask, TaskList myList) {
        try {
            makeEventFunction(currTask, myList);
        } catch (EmptyInputException e) {
            Ui.printEmptyInputMessage(currTask.trim());
        } catch (IllegalInputException e) {
            Ui.printIllegalInputMessage();
        }
    }

    public static void checkMarkUnmark(String currTask) throws EmptyInputException {
        String[] list = currTask.split(" ");
        if (list.length < 2) {
            if (list[0].equals("mark") || list[0].equals("unmark")) {
                throw new EmptyInputException();
            }
        }
    }

    /** Deletes the specific task by index */
    public static void deleteTask(String currTask, TaskList myList, Storage database) throws IllegalInputException {
        String[] list = currTask.split(" ");
        if (isNumeric(list[1]) && list.length == 2) {
            int indexToRemove = Integer.parseInt(list[1]) - 1;
            Ui.printDeletedMessage(myList, indexToRemove);
            myList.deleteTask(indexToRemove);
        } else {
            throw new IllegalInputException();
        }

        String stringToAdd = "";
        for (Task task : myList.tasks) {
            stringToAdd += database.stringToWrite(task).toString() + System.lineSeparator();
        }
        try {
            database.writeToFile(stringToAdd);
        } catch (IOException e) {
            Ui.printDeletingErrorMessage();
        }
    }

    /** Initialises delete task */
    public static void handleDeleteTask(String currTask, TaskList myList, Storage database) {
        try {
            deleteTask(currTask, myList, database);
        } catch (IllegalInputException e) {
            Ui.printIllegalInputMessage();
        } catch (IndexOutOfBoundsException e) {
            Ui.printIllegalInputMessage();
        }
    }

    /** Checks if given string is a number */
    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
