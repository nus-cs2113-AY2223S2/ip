import duke.data.DataManager;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todos;
import duke.utils.DukeException;
import duke.utils.Parser;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.utils.Ui.*;

public class Duke {
    public TaskList taskList;
    public Ui ui;
    public Parser parser;
    public Duke () {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser(this.taskList, this.ui);
    }
    public static Scanner input = new Scanner(System.in);
    /*    public static ArrayList<Task> list = new ArrayList<>();*/
    //public static String[] words = new String[10];
    /*    public static String[] phrases = new String[10];*/
    //public static int currentTaskNum = 0;
    public static String FILE_LOCATION = "./data/duke.txt";
    /*
    public static String LINE = "────────────────────────────────────────────────────────────────────────\n";
    public static String GENERAL_ERROR_MESSAGE = LINE + "Invalid input. Please try again! (=ಠᆽಠ=)\n" + LINE;
    public static String INVALID_NUM_ERROR_MESSAGE = LINE + "The task number is out of bound. Please try again! (=ಠᆽಠ=)\n" + LINE;
    public static String EVENT_TIME_ERROR_MESSAGE = LINE + "There is no start and end time for the event. " +
            "Please try again by using the keywords /from and /to! (=ಠᆽಠ=)\n" + LINE;
    public static String DEADLINE_TIME_ERROR_MESSAGE = LINE + "There is no deadline for this task. " +
            "Please try again by using the keywords /by! (=ಠᆽಠ=)\n" + LINE;
    public static String LOGO = " ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⡷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠋⠈⠻⣮⣳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣾⡿⠋⠀⠀⠀⠀⠙⣿⣿⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣶⣿⡿⠟⠛⠉⠀⠀⠀⠀⠀⠀⠀⠈⠛⠛⠿⠿⣿⣷⣶⣤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣾⡿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠻⠿⣿⣶⣦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⣀⣠⣤⣤⣀⡀⠀⠀⣀⣴⣿⡿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠿⣿⣷⣦⣄⡀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣄⠀⠀\n" +
            "⢀⣤⣾⡿⠟⠛⠛⢿⣿⣶⣾⣿⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠿⣿⣷⣦⣀⣀⣤⣶⣿⡿⠿⢿⣿⡀⠀\n" +
            "⣿⣿⠏⠀⢰⡆⠀⠀⠉⢿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⢿⡿⠟⠋⠁⠀⠀⢸⣿⠇⠀\n" +
            "⣿⡟⠀⣀⠈⣀⡀⠒⠃⠀⠙⣿⡆⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠇⠀\n" +
            "⣿⡇⠀⠛⢠⡋⢙⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀\n" +
            "⣿⣧⠀⠀⠀⠓⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠛⠋⠀⠀⢸⣧⣤⣤⣶⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⡿⠀⠀\n" +
            "⣿⣿⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠻⣷⣶⣶⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⠁⠀⠀\n" +
            "⠈⠛⠻⠿⢿⣿⣷⣶⣦⣤⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⡏⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠉⠙⠛⠻⠿⢿⣿⣷⣶⣦⣤⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠿⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⡄⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠙⠛⠻⠿⢿⣿⣷⣶⣦⣤⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⡄⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠛⠛⠿⠿⣿⣷⣶⣶⣤⣤⣀⡀⠀⠀⠀⢀⣴⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⡿⣄\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠛⠛⠿⠿⣿⣷⣶⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣹\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⠀⠀⠀⠀⠀⢸⣧\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣆⠀⠀⠀⠀⠀⠀⢀⣀⣠⣤⣶⣾⣿⣿⣿⣿⣤⣄⣀⡀⠀⠀⠀⣿\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⢿⣻⣷⣶⣾⣿⣿⡿⢯⣛⣛⡋⠁⠀⠀⠉⠙⠛⠛⠿⣿⣿⡷⣶⣿\n";
    public static String GREETING = LINE +
            " Meow! I'm Ashy ฅ(^•ܫ•^)ฅ\n" +
            " What can I do for you?\n" +
            LINE;
    public static String FAREWELL_MESSAGE =
            " Bye. Hope to see you again soon meow!\n" + LINE;
*/
    public boolean shouldExit = false;

    public void setShouldExit() {
        this.shouldExit = true;
    }

/*    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printTotalTasks(int currentTaskNum) {
        if (currentTaskNum == 1) {
            System.out.println("Now you have " + currentTaskNum + " task in the list.");
        } else {
            System.out.println("Now you have " + currentTaskNum + " tasks in the list.");
        }
    }*/

/*    public int getMarkPosition(String content) {
        //words = content.split(" ");
        return Integer.parseInt(content) - 1;
    }*/
    
/*    public void toggleMark(String content, boolean shouldMarkAsDone) throws DukeException {
        int posOfMark = Integer.parseInt(content) - 1;
        if (!(posOfMark >= 0 && posOfMark <= currentTaskNum)) {
            printErrorMessage(INVALID_NUM_ERROR_MESSAGE);
            throw new DukeException();
        } else {
            if (shouldMarkAsDone) {
                list.get(posOfMark).markAsDone();
            } else {
                list.get(posOfMark).markAsUndone();
            }
        }
    }*/
/*
    private void processAddTaskRequest() {
        printAddTaskMessage();
        currentTaskNum++;
        printTotalTasks(currentTaskNum);
        System.out.println(LINE);
    }*/


    /*public void handleRequest(String userInput) throws DukeException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        String[] splitInput = userInput.split("", 2);
        String command = splitInput[0];
        String content = splitInput[1];
        switch (command) {
        case "mark":
            taskList.toggleMark(content, true);
        case "unmark":
            taskList.toggleMark(content, false);
        case "list":
            System.out.println("Here is your list!  ( ⓛ ω ⓛ✧)");
            TaskList.printList(taskList.currentTaskNum);
        case "todo":
*//*            list.add(new Todos(content));*//*
            taskList.processAddTaskRequest();
        case "event":
            phrases = content.split("/");
            if (phrases.length < 3) {
                ui.printErrorMessage(EVENT_TIME_ERROR_MESSAGE);
            } else {
                list.add(new Event(phrases[0], phrases[1], phrases[2]));
                taskList.processAddTaskRequest();
            }
        case "deadline":
            phrases = userInput.split("/by ");
            if (phrases.length < 2) {
                ui.printErrorMessage(DEADLINE_TIME_ERROR_MESSAGE);
            } else {
                list.add(new Deadline(phrases[0], phrases[1]));
                taskList.processAddTaskRequest();
            }
        case "delete":
            ui.printDeleteTaskMessage(Integer.parseInt(content) - 1);
            list.remove(Integer.parseInt(content) - 1);
            taskList.currentTaskNum--;
        case "find":
        case "guide":
        default:
            ui.printErrorMessage(GENERAL_ERROR_MESSAGE);
        }*/
       /* if (userInput.startsWith("mark")) {
            int posOfMark = getMarkPosition(userInput);
            toggleMark(posOfMark, true);
        } else if (userInput.startsWith("unmark")) {
            int posOfMark = getMarkPosition(userInput);
            toggleMark(posOfMark, false);
        } else if (userInput.equals("list")) {
            System.out.println("Here is your list!  ( ⓛ ω ⓛ✧)");
            printList(currentTaskNum);
        } else if (userInput.equals("bye")) {
            System.out.println(FAREWELL_MESSAGE);
            setShouldExit();
        } else if (userInput.startsWith("todo")) {
            list.add(new Todos(userInput.replace("mark ", "")));
            processAddTaskRequest();
        } else if (userInput.startsWith("deadline")) {
            phrases = userInput.split("/by ");
            if (phrases.length < 2) {
                printErrorMessage(DEADLINE_TIME_ERROR_MESSAGE);
            } else {
                list.add(new Deadline(phrases[0].replace("deadline ", ""), phrases[1]));
                processAddTaskRequest();
            }
        } else if (userInput.startsWith("event")) {
            phrases = userInput.split("/");
            if (phrases.length < 3) {
                printErrorMessage(EVENT_TIME_ERROR_MESSAGE);
            } else {
                list.add(new Event(phrases[0].replace("event ", ""), phrases[1], phrases[2]));
                processAddTaskRequest();
            }
        } else if (userInput.startsWith("delete")){
                printDeleteTaskMessage(getMarkPosition(userInput));
                list.remove(getMarkPosition(userInput));
                currentTaskNum--;
        } else {
            printErrorMessage(GENERAL_ERROR_MESSAGE);
        }*/

    
    public static void main(String[] args) throws DukeException, IOException {
        Duke obj = new Duke();
/*        Parser parser = new Parser(obj.taskList, obj.ui);*/
        System.out.println("Hello from\n" + LOGO);
        System.out.println(GREETING);
        while (!obj.shouldExit) {
            String userInput = input.nextLine();
            obj.parser.doCommand(userInput);
        }
/*        DataManager dataManager= new DataManager(FILE_LOCATION, obj.taskList.list);*/
/*        dataManager.writeToFileWithErrorHandler();*/
    }
}

