package duke;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidInputIDException;
import duke.exceptions.InvalidTaskFormatException;
import duke.exceptions.NoTaskException;
import duke.tasks.*;

import duke.storage.Storage;
import duke.ui.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static final String CHAR_SPACE = " ";

    // commands
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_SAVE = "save";

    // messages

    private static UI ui;
    private static TaskList tasks;

    public static void main(String[] args) {
        ui = new UI();
        tasks = new TaskList(Storage.read());
        Scanner scan = new Scanner(System.in);
        ui.printLogo();
        ui.greet();

        String input;
        do {
            input = scan.nextLine();
            boolean isExit = input.split(CHAR_SPACE)[0].equals(COMMAND_EXIT);
            if (isExit) {
                ui.printLine();
                handleStateExit();
                break;
            }
            if (!input.isEmpty()) {
                ui.printLine();
                executeInputCommand(input);
            }
        } while (true);

        scan.close();
    }

    private static void executeInputCommand(String s) {
        Scanner input = new Scanner(s);
        String command = input.next().toLowerCase();

        try {
            switch (command) {
            case COMMAND_LIST:
                handleCommandList();
                break;
            case COMMAND_MARK:
                handleCommandMark(input);
                break;
            case COMMAND_UNMARK:
                handleCommandUnmark(input);
                break;
            case COMMAND_TODO:
                handleCommandTodo(input);
                break;
            case COMMAND_DEADLINE:
                handleCommandDeadline(input);
                break;
            case COMMAND_EVENT:
                handleCommandEvent(input);
                break;
            case COMMAND_DELETE:
                handleCommandDelete(input);
                break;
            case COMMAND_SAVE:
                handleCommandSave();
                break;
            default:
                throw new InvalidCommandException();
            }
            // update saved tasks
            Storage.save(tasks.toJson());
        } catch (IOException e) {
            ui.printSaveStatus(false);
        } catch (Exception e) {
            ui.print(e.getMessage());
            ui.printLine();
        }
    }

    private static void addTask(Task taskObj) {
        tasks.add(taskObj);
        ui.printTaskAdded(taskObj.describe(), tasks.size());
    }

    private static String getTaskDetails(Scanner input) {
        // validate input
        if (!input.hasNextLine()) {
            return "";
        }
        String taskDetails = input.nextLine().trim();
        return ToDo.isValidInput(taskDetails)
                ? taskDetails
                : "";
    }

    private static void handleStateExit() {
        ui.printExit();
    }

    private static void handleCommandList() {
        String output = tasks.listAll();
        ui.print(output);
        ui.printLine();
    }

    private static void handleCommandMark(Scanner input) throws Exception {
        if (!input.hasNextInt()) {
            throw new InvalidInputIDException();
        }
        int taskNumber = input.nextInt();
        ui.print(tasks.setStatus(taskNumber - 1, true));
    }

    private static void handleCommandUnmark(Scanner input) throws Exception {
        if (!input.hasNextInt()) {
            throw new InvalidInputIDException();
        }
        int taskNumber = input.nextInt();
        ui.print(tasks.setStatus(taskNumber - 1, false));
    }

    private static void handleCommandTodo(Scanner input) throws InvalidTaskFormatException {
        // check if input matches required specifications
        String taskDetails = getTaskDetails(input);
        if (taskDetails.equals("")) {
            throw new InvalidTaskFormatException(TaskEnum.TODO);
        }

        // create task
        ArrayList<String> detailsArr = ToDo.convertInputIntoDetails(taskDetails);
        addTask(new ToDo(detailsArr));
    }

    private static void handleCommandDeadline(Scanner input) throws InvalidTaskFormatException {
        // check if input matches required specifications
        String taskDetails = getTaskDetails(input);
        if (taskDetails.equals("")) {
            throw new InvalidTaskFormatException(TaskEnum.DEADLINE);
        }

        // create task
        ArrayList<String> detailsArr = Deadline.convertInputIntoDetails(taskDetails);
        addTask(new Deadline(detailsArr));
    }

    private static void handleCommandEvent(Scanner input) throws InvalidTaskFormatException {
        // check if input matches required specifications
        String taskDetails = getTaskDetails(input);
        if (taskDetails.equals("")) {
            throw new InvalidTaskFormatException(TaskEnum.EVENT);
        }

        // create task
        ArrayList<String> detailsArr = Event.convertInputIntoDetails(taskDetails);
        addTask(new Event(detailsArr));
    }

    private static void handleCommandDelete(Scanner input) throws InvalidInputIDException {
        // check for valid task input
        if (!input.hasNextInt()) {
            throw new InvalidInputIDException();
        }
        int id = input.nextInt();
        if (id < 1 || id > tasks.size()) {
            throw new InvalidInputIDException();
        }

        Task toDelete = tasks.delete(id);
        ui.printTaskDeleted(toDelete.describe(), tasks.size());
    }

    private static void handleCommandSave() throws IOException {
        Storage.save(tasks.toJson());
        ui.printSaveStatus(true);
    }
}
