package orca;

import java.util.Scanner;

public class Orca {
    private static Ui ui;
    private static TaskList taskList;
    private static Storage storage;

    static final boolean FINISHED = true;

    static Scanner in = new Scanner(System.in);
    static String userInput = "";
    static CommandType commandType;
    static int taskNo;
    static Task newTask;

    public Orca() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList(storage.load());
    }

    public static void findCommandType() {
        if (userInput.equals("bye")) {
            commandType = CommandType.BYE;
        } else if (userInput.equals("list")) {
            commandType = CommandType.LIST;
        } else if (userInput.startsWith("mark")) {
            commandType = CommandType.MARK;
        } else if (userInput.startsWith("unmark")) {
            commandType = CommandType.UNMARK;
        } else if (userInput.startsWith("todo")) {
            commandType = CommandType.TODO;
        } else if (userInput.startsWith("deadline")) {
            commandType = CommandType.DEADLINE;
        } else if (userInput.startsWith("event")) {
            commandType = CommandType.EVENT;
        } else if (userInput.startsWith("delete")) {
            commandType = CommandType.DELETE;
        } else {
            commandType = CommandType.UNKNOWN;
        }
    }

    public static boolean isInputAvailable() {
        return in.hasNextLine();
    }

    public static void readUserInput() {
        userInput = in.nextLine();
    }

    public static int parseTaskNo(String userInput, int startIdx) throws OrcaException {
        try {
            return Integer.parseInt(userInput.substring(startIdx));
        } catch (NumberFormatException e) {
            throw new OrcaException("I cannot parse the integer.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new OrcaException("I cannot parse the integer.");
        }
    }

    public static boolean executeCommand() throws OrcaException {
        switch (commandType) {
            case BYE:
                ui.printByeMessage();
                storage.write(taskList.get());
                return FINISHED;
            case LIST:
                ui.printTasks(taskList.get());
                break;
            case MARK:
                taskNo = parseTaskNo(userInput, 5);
                taskList.markTask(taskNo);
                ui.printMarkedTask(taskList.get(taskNo - 1));
                break;
            case UNMARK:
                taskNo = parseTaskNo(userInput, 7);
                taskList.unmarkTask(taskNo);
                ui.printUnmarkedTask(taskList.get(taskNo - 1));
                break;
            case TODO:
                newTask = new Todo(userInput, 5);
                taskList.addTask(newTask);
                ui.printAddedTask(newTask, taskList.getSize());
                break;
            case DEADLINE:
                newTask = new Deadline(userInput, 9);
                taskList.addTask(newTask);
                ui.printAddedTask(newTask, taskList.getSize());
                break;
            case EVENT:
                newTask = new Event(userInput, 6);
                taskList.addTask(newTask);
                ui.printAddedTask(newTask, taskList.getSize());
                break;
            case DELETE:
                taskNo = parseTaskNo(userInput, 7);
                Task removedTask = taskList.deleteTask(taskNo);
                ui.printRemovedTask(removedTask, taskList.getSize());
                break;
            default:
                throw new OrcaException("Sorry. I can't understand what you mean. :(");
        }
        return !FINISHED;
    }

    public static void runOrca() {
        boolean isFinished = false;
        while (isInputAvailable()) {
            readUserInput();
            findCommandType();
            try {
                isFinished = executeCommand();
            } catch (OrcaException e) {
                System.out.println(e.getMessage());
            }
            if (isFinished) {
                break;
            }
        }
    }

    public static void finishProcess() {
        in.close();
    }

    public void run() {
        ui.printGreetingMessage();
        runOrca();
        finishProcess();
    }

    public static void main(String[] args) {
        new Orca().run();
    }
}
