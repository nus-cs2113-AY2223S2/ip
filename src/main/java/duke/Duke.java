package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {
    // Definition lists and methods here:

    //Fixed strings for easier index access for different task categories
    static final String BY = "/by";
    static final String FROM = "/from";
    static final String TO = "/to";

    public static void printMargin(){
        String MARGIN = "*----------------------------*";
        System.out.println(MARGIN);
    }
    // Storage of tasks
    public static Task[] tasksArray = new Task[100];
    public static int taskCount = 0;
    // Begin Program
    public static void welcomeMessage(){
        String logo = "┊┊┊┊┊╭╭╭╮╮╮┊┊┊┊ \n" +
                "┊┊┊┊┊╰╰╲╱╯╯┊┊┊┊ \n" +
                "┊┏╮╭┓╭━━━━━━╮┊┊ \n" +
                "┊╰╮╭╯┃┈┈┈┈┈┈┃┊┊ \n" +
                "┊┊┃╰━╯┈┈╰╯┈┈┃┊┊ \n" +
                "┊┊┃┈┈┈┈┈┈┈╰━┫┊┊ \n" +
                "╲╱╲╱╲╱╲╱╲╱╲╱╲╱╲\n" ;
        printMargin();
        System.out.println(logo);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        printMargin();
    }
    // Terminate program
    public static void endProgram(){
        printMargin();
        System.out.println("Bye. Hope to see you again soon!");
        printMargin();
    }
    // For 'list' command
    public static void accessList(){
        printMargin();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasksArray[i].toString());
        }
        printMargin();
    }
    // Marking task as done
    public static void markTask(int taskIdx){
        tasksArray[taskIdx-1].setDone(true);
        printMargin();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " +  tasksArray[taskIdx-1].getStatusIcon() +
                tasksArray[taskIdx-1].description);
        printMargin();
    }
    // Unmarking task
    public static void unmarkTask(int taskIdx){
        tasksArray[taskIdx-1].setDone(false);
        printMargin();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " +  tasksArray[taskIdx-1].getStatusIcon()  +
                tasksArray[taskIdx-1].description);
        printMargin();
    }

    // Update task addition sections[todo, event , deadline]:
    public static void taskAdded(int taskCount){
        printMargin();
        System.out.println("Got it. I've added this task:");
        System.out.println("  "+ tasksArray[taskCount].toString());
        taskCount++ ;
        System.out.println("Now you have "+ taskCount + " tasks in the list.");
        printMargin();
    }


    // How the program runs based on user's input commands
    public static void enterCommand() {
        String userCommand;
        boolean hasProgramEnded = false;
        int taskIdx;
        while(!hasProgramEnded) {
            Scanner input = new Scanner(System.in);
            userCommand = input.nextLine();
            // Split user's input into individual words for easier index access
            String[] wordSeparator = userCommand.split(" ");

            // Identify first word in the user's input and execute corresponding command
            String KEYWORD = wordSeparator[0];

            // Place remaining words into another string
            String details = " ";
            for (int i = 1; i < wordSeparator.length; i++) {
                details = details + wordSeparator[i];
            }


            switch (KEYWORD) {
            case "bye":
                hasProgramEnded = true;
                break;

            case "list":
                accessList();
                break;

            case "mark":
                try {
                    taskIdx = Integer.parseInt(wordSeparator[1]);
                    markTask(taskIdx);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printMargin();
                    System.out.println("Please key in a number to mark!");
                    printMargin();

                } catch (NullPointerException e) {
                    printMargin();
                    System.out.println("This task does not exist!");
                    printMargin();
                }
                break;

            case "unmark":
                try {
                    taskIdx = Integer.parseInt(wordSeparator[1]);
                    unmarkTask(taskIdx);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printMargin();
                    System.out.println("Please key in a number to unmark!");
                    printMargin();

                } catch (NullPointerException e) {
                    printMargin();
                    System.out.println("This task does not exist!");
                    printMargin();
                }
                break;

            case "todo":
                try {
                    if (wordSeparator.length == 1) {
                        throw new DukeException();
                    }
                    tasksArray[taskCount] = new Todo(details);
                    taskAdded(taskCount);
                    taskCount++;
                } catch (DukeException e) {
                    e.todoError();
                }
                break;


            case "deadline":
                try {
                    if (wordSeparator.length == 1) {
                        throw new DukeException();
                    }
                    String[] deadlineTaskSeparator = details.split(BY);
                    // Get task from user's input
                    String deadlineTask = deadlineTaskSeparator[0];
                    // Get due date from user's input
                    String dueBy = deadlineTaskSeparator[1];
                    tasksArray[taskCount] = new Deadline(deadlineTask, dueBy);
                    taskAdded(taskCount);
                    taskCount++;
                } catch (DukeException e) {
                    e.deadlineError();
                }
                break;


            case "event":
                try {
                    if (wordSeparator.length == 1) {
                        throw new DukeException();
                    }
                    String[] eventTaskSeparator = userCommand.split(FROM);
                    // Get task from user's input
                    String eveTask = eventTaskSeparator[0];
                    String eventPeriod = eventTaskSeparator[1];
                    //Further split the event duration to get start and end times
                    String[] eventPeriodSeparator = eventPeriod.split(TO);
                    // Get event start time
                    String startTime = eventPeriodSeparator[0];
                    // Get event end time
                    String endTime = eventPeriodSeparator[1];
                    tasksArray[taskCount] = new Event(eveTask, startTime, endTime);
                    taskAdded(taskCount);
                    taskCount++;
                } catch (DukeException e) {
                    e.eventError();
                }
                break;

            default:
                printMargin();
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printMargin();
                break;
            }
        }
    }

    // Run program here:
    public static void main(String[] args) {
        welcomeMessage();
        enterCommand();
        endProgram();
    }
}
