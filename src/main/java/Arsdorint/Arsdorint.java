package Arsdorint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Arsdorint.UI.TextUI;
import Arsdorint.command.*;
import Arsdorint.data.Storage;
import Arsdorint.data.TaskList;
import Arsdorint.parser.TaskParser;
import Arsdorint.task.Deadline;
import Arsdorint.task.Event;
import Arsdorint.task.Task;
import Arsdorint.task.Todo;


import static Arsdorint.MessageList.*;
import static Arsdorint.parser.TaskParser.showToUser;

public class Arsdorint {
    static Scanner input = new Scanner(System.in);
    public static String logo = "    ___                         _                                 _\n"
            + "   / _ \\     _____   _____  ___| |   ___    _____   _   _____   _| |_\n"
            + "  / /_\\ \\   /  ___| /  __/ /  _  |  / _ \\  /  ___| | | |  _  \\ |_   _|\n"
            + " / _____ \\  | /    __\\ \\   | |_| | | |_| | | /     | | | | | |   | |\n"
            + "/_/     \\_\\ |_|   /____/   \\_____|  \\___/  |_|     |_| |_| |_|   |_|\n";

    public static ArrayList<Task> toDoList = new ArrayList<>(MAX_NUM_OF_TASKS);
    static boolean isRunning = true;
    private TextUI UI;
    private TaskList taskList = new TaskList();
    public void exit() {
        UI.showExitMessage();
        UI.input.close();
        System.exit(0);
    }

    private static void wrongCommandMessage() {
        System.out.println("=( OOPS!!! I'm sorry, but I don't know what that means :-(\n" + MESSAGE_DIVIDER);
    }

    public static void list() {
        showToUser(MESSAGE_DIVIDER_LIST);
        for (int i = 0; i < Task.numOfTasks; i++) {
            System.out.print((i + 1) + ".");
            toDoList.get(i).printTask();
        }
        showToUser(MESSAGE_DIVIDER);
    }

    /*public static void add(String command) {
        String newTaskType = command.split(" ")[0];
        String[] taskDescription =
                command.replace(newTaskType, "").trim().split("/");
        switch (newTaskType.toLowerCase()) {
            case "todo":
                addToDo(taskDescription);
                break;
            case "deadline":
                addDeadline(taskDescription);
                break;
            case "event":
                addEvent(taskDescription);
                break;
            default:
                break;
        }
    }

    private static void commandErrorHandler(String errorMessage) {
        switch (errorMessage) {
            case "bye":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_BYE, MESSAGE_DIVIDER);
                break;
            case "list":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_LIST, MESSAGE_DIVIDER);
                break;
            case "mark":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_MARK, MESSAGE_DIVIDER);
                break;
            case "unmark":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_UNMARK, MESSAGE_DIVIDER);
                break;
            case "todo":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_TODO, MESSAGE_DIVIDER);
                break;
            case "deadline":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_DEADLINE, MESSAGE_DIVIDER);
                break;
            case "event":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_EVENT, MESSAGE_DIVIDER);
                break;
            case "delete":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_DELETE, MESSAGE_DIVIDER);
                break;
            default:
                showToUser(MESSAGE_DIVIDER, errorMessage, MESSAGE_DIVIDER);
                break;
        }
    }*/

    public static void addTaskMessage() {
        showToUser(MESSAGE_DIVIDER);
        System.out.println("\nGot it. I've added this task:\n" + "\t");
        toDoList.get(toDoList.size() - 1).printTask();
        System.out.println("\t" + "Now you have " + Integer.toString(Task.numOfTasks) + " tasks in the list.");
        showToUser(MESSAGE_DIVIDER);
    }


    public static void main(String[] args) {
        new Arsdorint().run();
        /*helloMessage();
        load();
        while (isRunning) {
            command();
        }*/
    }

    public void run() {
        start();
        mainLoop();
        exit();
    }

    private void start() {
        this.UI = new TextUI();
        UI.showHelloMessage();
        UI.showToUser(Storage.load());
    }

    private void mainLoop() {
        Command command;
        do {
            String userCommandText = UI.getUserCommand();
            command = new TaskParser().parsedCommand(userCommandText);
            CommandRes res = executeCommand(command);
            UI.showResToUser(res);
            UI.showToUser(Storage.save());
        } while (!CommandExit.isExit(command));
    }
    private CommandRes executeCommand(Command command) {
        return command.execute();
    }
}
