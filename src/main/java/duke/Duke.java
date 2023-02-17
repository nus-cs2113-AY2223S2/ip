package duke;

import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidTaskFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskEnum;
import duke.tasks.ToDo;

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
    private static final String MESSAGE_COMMAND_UNRECOGNISED = "Unrecognised command, try again.";
    private static final String MESSAGE_TASKS_AVAILABLE = "Here are the tasks in your list:";
    private static final String MESSAGE_TASKS_INVALID_ID = "Invalid task ID entered.";
    private static final String MESSAGE_TASKS_MARKED = "Nice! I've marked this task as done:";
    private static final String MESSAGE_TASKS_NONE = "There are no tasks available.";
    private static final String MESSAGE_TASKS_UNMARKED = "OK, I've marked this task as not done yet:";

    private static final UI ui = new UI();
    // data
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        tasks = Storage.getTasks();
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
                throw new InvalidInputException(MESSAGE_COMMAND_UNRECOGNISED);
            }
            // update saved tasks
            Storage.writeTasks(tasks);
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

    private static void setTaskStatus(int id, boolean isCompleted) throws InvalidInputException {
        try {
            if (id >= tasks.size() || id < 0) {
                throw new IndexOutOfBoundsException();
            }
            tasks.get(id).setIsCompleted(isCompleted);
            String output = isCompleted
                            ? MESSAGE_TASKS_MARKED + "\n"
                            : MESSAGE_TASKS_UNMARKED + "\n";
            output += tasks.get(id).describe();
            ui.print(output);
            ui.printLine();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(tasks.size() == 0
                                            ? MESSAGE_TASKS_NONE
                                            : MESSAGE_TASKS_INVALID_ID);
        }
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
        String output = tasks.size() == 0
                        ? MESSAGE_TASKS_NONE
                        : MESSAGE_TASKS_AVAILABLE + "\n";
        // adds tasks to output, if any
        // combine details of tasks into a single string
        for (int i = 0; i < tasks.size(); ++i) {
            output += (i + 1) + "." // number
                    + tasks.get(i).describe() + "\n";
        }
        ui.print(output);
        ui.printLine();
    }

    private static void handleCommandMark(Scanner input) throws InvalidInputException {
        if (!input.hasNextInt()) {
            throw new InvalidInputException(MESSAGE_TASKS_INVALID_ID);
        }
        int taskNumber = input.nextInt();
        setTaskStatus(taskNumber - 1, true);
    }

    private static void handleCommandUnmark(Scanner input) throws InvalidInputException {
        if (!input.hasNextInt()) {
            throw new InvalidInputException(MESSAGE_TASKS_INVALID_ID);
        }
        int taskNumber = input.nextInt();
        setTaskStatus(taskNumber - 1, false);
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

    private static void handleCommandDelete(Scanner input) throws InvalidInputException {
        // check for valid task input
        if (!input.hasNextInt()) {
            throw new InvalidInputException(MESSAGE_TASKS_INVALID_ID);
        }
        int id = input.nextInt();
        if (id < 1 || id > tasks.size()) {
            throw new InvalidInputException(MESSAGE_TASKS_INVALID_ID);
        }

        Task toDelete = tasks.get(id - 1);
        tasks.remove(id - 1);
        ui.printTaskDeleted(toDelete.describe(), tasks.size());
    }

    private static void handleCommandSave() throws IOException {
        Storage.writeTasks(tasks);
        ui.printSaveStatus(true);
    }
}
