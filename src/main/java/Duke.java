import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todos;
import duke.utils.DukeException;

import java.util.Scanner;
public class Duke {
    public static Scanner input = new Scanner(System.in);
    public static Task[] list= new Task[100];
    public static String[] words = new String[10];
    public static String[] phrases = new String[10];
    public static int currentTaskNum = 0;
    public static String LINE = "────────────────────────────────────────────────────────────────────────\n";
    public static String GENERAL_ERROR_MESSAGE = LINE + "Invalid input. Please try again! (=ಠᆽಠ=)\n" + LINE;
    public static String INVALID_NUM_ERROR_MESSAGE = LINE + "The duke.task number is out of bound. Please try again! (=ಠᆽಠ=)\n" + LINE;
    public static String EVENT_TIME_ERROR_MESSAGE = LINE + "There is no start and end time for the event. " +
            "Please try again by using the keywords /from and /to! (=ಠᆽಠ=)\n" + LINE;
    public static String DEADLINE_TIME_ERROR_MESSAGE = LINE + "There is deadline for this duke.task. " +
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

    public boolean shouldExit = false;

    public void setShouldExit () {
        this.shouldExit = true;
    }

    public static void printList(int currentTaskNum) {
        int currentPrintedTask = 0;
        int placeHolder = currentTaskNum;
        System.out.println(LINE);
        if (placeHolder == 0) {
            System.out.println("No duke.task.Task! 〲⚆ﻌ⚆〉");
        } else {
            while (placeHolder > 0) {
                System.out.println(currentPrintedTask + 1 + ". " + list[currentPrintedTask].toString());
                currentPrintedTask++;
                placeHolder--;
            }
        }
        System.out.println(LINE);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printTotalTasks(int currentTaskNum) {
        if (currentTaskNum == 1) {
            System.out.println("Now you have " + currentTaskNum + " duke.task in the list.");
        } else {
            System.out.println("Now you have " + currentTaskNum + " tasks in the list.");
        }
    }

    public int getMarkPosition(String userInput) {
        words = userInput.split(" ");
        return Integer.parseInt(words[1]) - 1;
    }
    
    public void toggleMark(int posOfMark, boolean shouldMarkAsDone) throws DukeException {
        if (!(posOfMark >= 0 && posOfMark <= currentTaskNum)) {
            printErrorMessage(INVALID_NUM_ERROR_MESSAGE);
            throw new DukeException();
        } else {
            if (shouldMarkAsDone) {
                list[posOfMark].markAsDone();
            } else {
                list[posOfMark].markAsUndone();
            }
        }
    }

    private void processAddTaskRequest() {
        printAddTaskMessage();
        currentTaskNum++;
        printTotalTasks(currentTaskNum);
        System.out.println(LINE);
    }

    private static void printAddTaskMessage() {
        System.out.println(LINE + "Got it. I've added this duke.task:\n"
                + "  "
                + list[currentTaskNum].toString()
                + System.lineSeparator());
    }

    public void handleRequest(String userInput) throws DukeException {
        if (userInput.startsWith("mark")) {
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
            list[currentTaskNum] = new Todos(userInput.replace("mark ", ""));
            processAddTaskRequest();
        } else if (userInput.startsWith("deadline")) {
            phrases = userInput.split("/by ");
            if (phrases.length < 2) {
                printErrorMessage(DEADLINE_TIME_ERROR_MESSAGE);
            } else {
                list[currentTaskNum] = new Deadline(phrases[0].replace("deadline ", ""), phrases[1]);
                processAddTaskRequest();
            }
        } else if (userInput.startsWith("event")) {
            phrases = userInput.split("/");
            if (phrases.length < 3) {
                printErrorMessage(EVENT_TIME_ERROR_MESSAGE);
            } else {
                list[currentTaskNum] = new Event(phrases[0].replace("event ", ""), phrases[1], phrases[2]);
                processAddTaskRequest();
            }
        } else {
            printErrorMessage(GENERAL_ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) throws DukeException {
        Duke obj = new Duke();
        System.out.println("Hello from\n" + LOGO);
        System.out.println(GREETING);
        while (!obj.shouldExit) {
            String userInput = input.nextLine();
            obj.handleRequest(userInput);
        }
    }
}
