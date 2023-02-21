package duke.ui;

import duke.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String LOGO = " _______             __                 \n" +
            "/       \\           /  |                \n" +
            "$$$$$$$  | __    __ $$ |   __   ______  \n" +
            "$$ |  $$ |/  |  /  |$$ |  /  | /      \\ \n" +
            "$$ |  $$ |$$ |  $$ |$$ |_/$$/ /$$$$$$  |\n" +
            "$$ |  $$ |$$ |  $$ |$$   $$<  $$    $$ |\n" +
            "$$ |__$$ |$$ \\__$$ |$$$$$$  \\ $$$$$$$$/ \n" +
            "$$    $$/ $$    $$/ $$ | $$  |$$       |\n" +
            "$$$$$$$/   $$$$$$/  $$/   $$/  $$$$$$$/ ";

    private static final String GOODBYE = "" +
            "   ______                     __    __                 \n" +
            "  / ____/  ____   ____   ____/ /   / /_    __  __  ___ \n" +
            " / / __   / __ \\ / __ \\ / __  /   / __ \\  / / / / / _ \\\n" +
            "/ /_/ /  / /_/ // /_/ // /_/ /   / /_/ / / /_/ / /  __/\n" +
            "\\____/   \\____/ \\____/ \\__,_/   /_.___/  \\__, /  \\___/ \n" +
            "                                        /____/         ";
    private static final String DIVIDER = "_______________________________________________________________";

    private static final String WELCOME = "\"Hello! I'm Duke!\" \n\"What can I do for you?\"";
    private static final String FAREWELL = "Bye! Hope to see you again!";
    private static final String SEARCH_HEADER = "Here are the matching tasks in your list:";
    private static final String EMPTYTASKEXCEPTION_MSG = "Task description cannot be empty!";
    private static final String ILLEGALCOMMANDEXCEPTION_MSG = "Illegal Command entered. Type 'help' for assistance.";
    private static final String NUMBERFORMATEXCEPTION_MSG = "You can only use this command with a valid integer";

    private static final String INVALID_DEADLINE_EVENT_MSG = "Invalid Deadline or Event Command. Type \'help\' " +
            "for more assistance";
    private static final String INVALID_ID = "Not a valid ID";

    private final Scanner in;
    private final PrintStream out;

    public Ui(){
        this(System.in, System.out);
    }
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    private boolean canIgnore(String inputLine){
        return inputLine.trim().isEmpty();
    }

    public void showException(String message){
        switch(message) {
        case "EmptyTaskException":
            out.println(EMPTYTASKEXCEPTION_MSG);
            break;
        case "NumberFormatException":
            out.println(NUMBERFORMATEXCEPTION_MSG);
            break;
        case "IllegalCommandException":
            out.println(ILLEGALCOMMANDEXCEPTION_MSG);
            break;
        case "Invalid ID":
            out.println(INVALID_ID);
        case "Invalid Deadline or Event":
            out.println(INVALID_DEADLINE_EVENT_MSG);
        default:
            break;
        }
    }
    public String getUserCommand(){
        out.print("Enter Command: ");
        String fullInputLine = in.nextLine();
        while(canIgnore(fullInputLine)){
            fullInputLine = in.nextLine();
        }
        showToUser("[Command entered: " + fullInputLine + "]");
        return fullInputLine;
    }

    public void showToUser(String ... message) {
        for (String m : message){
            out.println(m);
        }
        out.println(DIVIDER);
    }

    public void showWelcome(){
        showToUser(DIVIDER, DIVIDER, LOGO, DIVIDER, DIVIDER, WELCOME, DIVIDER);
    }

    public void handleExit(){
        showToUser(FAREWELL, DIVIDER, DIVIDER, GOODBYE, DIVIDER);
    }

    public void showSuccessfulAdd(Task task){
        out.println("Got it. I've added this to duke: ");
        out.println("    " + task.toString());
        out.println(DIVIDER);
    }

    public void showSuccessfulMark(Task task){
        out.println("Nice! I've marked this task as done: ");
        out.println(task.toString());
        out.println(DIVIDER);
    }
    public void showSuccessfulUnmark(Task task){
        out.println("Nice! I've marked this task as not done yet: ");
        out.println(task.toString());
        out.println(DIVIDER);
    }

    public void printHelp(){ //duke.Duke prints this if none of the duke.commands below are used
        System.out.println("Looks like you did not enter a valid duke.Duke.command.\n");
        System.out.println("Command list:");
        System.out.println("    1. todo: Adds a duke.Duke.task to be done");
        System.out.println("        Usage example: todo eat dinner");
        System.out.println("    2. deadline: Adds a duke.Duke.task to be done, use /by to specify when it is due");
        System.out.println("        Usage example: deadline submit homework /by 3pm tonight");
        System.out.println("    3. event: Adds an upcoming event. Use /from and /to to specify when it starts " +
                "and ends respectively.");
        System.out.println("        Usage example: event lecture this evening /from 4pm /to 6pm");
        System.out.println("    4. list: lists all tasks recorded by duke. Only enter the keyword.");
        System.out.println("    5. mark: Marks a specific duke.Duke.task as done. Command requires a specific " +
                "index starting from 1");
        System.out.println("        Usage example: mark 2 (assuming that there are more than two tasks in the list");
        System.out.println("    6. bye: Exits duke");
        out.println(DIVIDER);
    }
    public void showSuccessfulDelete(Task task, int size){
        System.out.println("Noted. I've removed this task: ");
        System.out.println("    " + task.toString());
        System.out.println("Now you have " +  + size + " tasks in the list");
        out.println(DIVIDER);
    }
    public void listTasks(ArrayList<Task> tasks){
        for (int i = 0; i < tasks.size(); i++){
            System.out.print((i+1)+ ".");
            System.out.println(tasks.get(i).toString());
        }
        out.println(DIVIDER);
    }
    public void searchResults(ArrayList<Task> tasks){
        out.println(SEARCH_HEADER);
        for (int i = 0; i < tasks.size(); i++){
            out.println("   "+ i + ". "+ tasks.get(i).toString());
        }
        out.println(DIVIDER);
    }
}
