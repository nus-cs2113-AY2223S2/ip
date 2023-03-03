package parser;

import file.storage.Storage;

import utility.Ui;
import utility.commandChecker;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the whole session of Duke's communication.
 * It creates an arraylist that stores all tasks that the user has given in this session.
 * It also parses the input that the user has entered. After that, it sends the broken down inputs to
 * the commandChecker class, where the inputs are validated.
 * If there are no errors in the input, it is executed.
 */
public class DukeSession {
    private static final String INPUT_ERROR_DETECTED = "Invalidate";

    private ArrayList<Task> actions = new ArrayList<>();

    /**
     * Calls the function setUpFile in the Storage class. It is used here to ensure arraylist is already setup.
     */
    public void setUpArrayList() {
        Storage.setUpFile(actions);
    }

    /**
     * This method parses the inputs into decisions and dates.
     * It then calls the method of commandChecker, hasErrors.
     * hasErrors will then validate the inputs and then notify DukeSession of any errors caught.
     * If there are no errors detected, it will call the method handleInputs where they are processed.
     * If errors are detected, user input is discarded.
     */
    public void execute() {
        String line;
        String[] decisions;
        String[] dates;
        Scanner in = new Scanner(System.in);
        do {
            line = in.nextLine();
            decisions = line.split(Ui.DEFAULT_LINE_SEPARATOR);
            dates = line.split(Ui.DEFAULT_FLAG_SEPARATOR);
            commandChecker currentLoop = new commandChecker(decisions, dates, actions.size());
            if (currentLoop.hasErrors()) {
                decisions[0] = INPUT_ERROR_DETECTED;
            }
            handleInputs(line, decisions, dates);
        } while (!decisions[0].equals(Ui.DEFAULT_EXIT));
        Storage.saveFile(actions);
        Ui.printExitMessage();
    }

    private void handleInputs(String line, String[] decisions, String[] dates) {
        switch (decisions[0]) {
        case Ui.DEFAULT_ECHO:
            System.out.println(findTaskDetails(line));
            break;
        case Ui.DEFAULT_TODO:
            handleToDo(line);
            break;
        case Ui.DEFAULT_EVENT:
            handleEvent(dates);
            break;
        case Ui.DEFAULT_DEADLINE:
            handleDeadline(dates);
            break;
        case Ui.DEFAULT_MARK_TASK:
            handleMarkTask(decisions);
            break;
        case Ui.DEFAULT_UNMARK_TASK:
            handleUnmarkTask(decisions);
            break;
        case Ui.DEFAULT_LIST_ALL_TASKS:
            handleList();
            break;
        case Ui.DEFAULT_DELETE:
            handleDeleteTask(decisions);
            break;
        case Ui.DEFAULT_FIND:
            handleFindTask(line);
            break;
        case Ui.DEFAULT_EXIT:
            break;
        default:
            Ui.printCurrentSupportedActions();
        }
    }

    private void handleToDo(String line) {
        Task toBeAdded = new Todo(findTaskDetails(line));
        actions.add(toBeAdded);
        Ui.printAcknowledgement(toBeAdded, actions.size());
    }

    private void handleEvent(String[] dates) {
        Task toBeAdded = new Event(findTaskDetails(dates[0]), findTaskDetails(dates[1]), findTaskDetails(dates[2]));
        actions.add(toBeAdded);
        Ui.printAcknowledgement(toBeAdded, actions.size());
    }

    private void handleDeadline(String[] dates) {
        Task toBeAdded = new Deadline(findTaskDetails(dates[0]), findTaskDetails(dates[1]));
        actions.add(toBeAdded);
        Ui.printAcknowledgement(toBeAdded, actions.size());
    }

    private void handleMarkTask(String[] decisions) {
        int taskNumber = Integer.parseInt(decisions[1]) - 1;
        actions.get(taskNumber).mark();
        Ui.printDoneMarkingTasks(actions.get(taskNumber));
    }

    private void handleUnmarkTask(String[] decisions) {
        int taskNumber = Integer.parseInt(decisions[1]) - 1;
        actions.get(taskNumber).unmark();
        Ui.printDoneMarkingTasks(actions.get(taskNumber));
    }

    private void handleList() {
        for (int i = 0; i < actions.size(); i++) {
            Ui.printListElement(i, actions.get(i));
        }
    }

    private void handleDeleteTask(String[] decisions) {
        int taskNumber = Integer.parseInt(decisions[1]) - 1;
        Ui.printDeleteAcknowledgement(actions.get(taskNumber), actions.size() - 1);
        actions.remove(taskNumber);
    }

    private void handleFindTask(String line) {
        String termToFind = findTaskDetails(line);
        int iterator = 0;
        int numberOfPrintedTasks = 0;
        for (Task searchTerm : actions) {
            boolean containsMatchingTerm = searchTerm.getDescription().toLowerCase().contains(termToFind.toLowerCase());
            if (containsMatchingTerm) {
                Ui.printListElement(iterator, actions.get(iterator));
                numberOfPrintedTasks++;
            }
            iterator++;
        }
        if (numberOfPrintedTasks > 0) {
            Ui.printFindAcknowledgement();
        } else if (numberOfPrintedTasks == 0) {
            Ui.printCannotFindAcknowledgement();
        } else {
            Ui.printDefaultErrorMessage();
        }
    }

    private static String findTaskDetails(String line) {
        int correctLineIndex = line.indexOf(Ui.DEFAULT_LINE_SEPARATOR) + 1;
        return line.substring(correctLineIndex);
    }

}
