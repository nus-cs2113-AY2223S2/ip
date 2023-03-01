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


public class DukeSession {
    private ArrayList<Task> actions = new ArrayList<>();

    public void setUpArrayList() {
        Storage.setUpFile(actions);
    }

    public void execute() {
        String line;
        String[] decisions;
        String[] dates;
        Scanner in = new Scanner(System.in);
        do {
            line = in.nextLine();
            decisions = line.split(" ");
            dates = line.split("/");
            commandChecker currentLoop = new commandChecker(decisions, dates, actions.size());
            if (currentLoop.hasErrors()) {
                decisions[0] = "Invalidate";
            }
            handleInputs(line, decisions, dates);
        } while (!decisions[0].equals("bye"));
        Storage.saveFile(actions);
        System.out.println("That's all from me! Goodbye!");
    }

    private void handleInputs(String line, String[] decisions, String[] dates) {
        switch (decisions[0]) {
            case "echo":
                System.out.println(findTaskDetails(line));
                break;
            case "todo":
                handleToDo(line);
                break;
            case "event":
                handleEvent(dates);
                break;
            case "deadline":
                handleDeadline(dates);
                break;
            case "mark":
                handleMarkTask(decisions);
                break;
            case "unmark":
                handleUnmarkTask(decisions);
                break;
            case "list":
                handleList();
                break;
            case "delete":
                handleDeleteTask(decisions);
                break;
            case "find":
                handleFindTask(line);
                break;
            case "bye":
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
        Ui.print(termToFind);
        int iterator = 0;
        int printedTasks = 0;
        for (Task searchTerm : actions) {
            if (searchTerm.getDescription().contains(termToFind)) {
                Ui.printListElement(iterator, actions.get(iterator));
                printedTasks++;
            }
            iterator++;
        }
        if (printedTasks > 0) {
            Ui.printFindAcknowledgement();
        } else if (printedTasks == 0) {
            Ui.printCannotFindAcknowledgement();
        } else {
            Ui.print("Oh dear, Something has went wrong!");
        }
    }

    private static String findTaskDetails(String line) {
        return line.substring(line.indexOf(" ") + 1);
    }


}
