package orca;

import java.util.Scanner;

public class Orca {
    static final boolean FINISHED = true;

    private static Ui ui;
    private static TaskList taskList;
    private static Storage storage;
    private static Parser parser;

    static Scanner in = new Scanner(System.in);

    public Orca() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList(storage.load());
        parser = new Parser();
    }


    /**
     * @return boolean true if there is input available, false otherwise.
     */
    public static boolean isInputAvailable() {
        return in.hasNextLine();
    }


    /**
     * @return String user input
     */
    public static String readUserInput() {
        return in.nextLine();
    }



    /**
     * @param commandType
     * @param userInput
     * @return boolean
     * @throws OrcaException
     */
    public static boolean executeCommand(CommandType commandType, String userInput)
            throws OrcaException {
        int taskNo;
        Task newTask;
        switch (commandType) {
            case BYE:
                ui.printByeMessage();
                storage.write(taskList.get());
                return FINISHED;
            case LIST:
                ui.printTasks(taskList.get());
                break;
            case MARK:
                taskNo = parser.ParseTaskNo(userInput, 5);
                taskList.markTask(taskNo);
                ui.printMarkedTask(taskList.get(taskNo - 1));
                break;
            case UNMARK:
                taskNo = parser.ParseTaskNo(userInput, 7);
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
                taskNo = parser.ParseTaskNo(userInput, 7);
                Task removedTask = taskList.deleteTask(taskNo);
                ui.printRemovedTask(removedTask, taskList.getSize());
                break;
            case FIND:
                String keyword = parser.ParseKeyword(userInput, 5);
                ui.printFoundTasks(taskList.findTasks(keyword));
                break;
            default:
                throw new OrcaException("Sorry. I can't understand what you mean. :(");
        }
        return !FINISHED;
    }

    public static void runOrca() {
        boolean isFinished = false;
        while (isInputAvailable()) {
            String userInput = readUserInput();
            CommandType commandType = parser.findCommandType(userInput);
            try {
                isFinished = executeCommand(commandType, userInput);
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
