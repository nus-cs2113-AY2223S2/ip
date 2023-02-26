package Handlers;

import java.util.Scanner;

public class Ui {
    /**
     * Takes in user inputs, extracts the command and calls the relevant methods in
     * TaskManager
     */
    public static void takeUserInputs() {
    
        boolean isComplete = false;
        Scanner in = new Scanner(System.in);
    
        printHello();
        printLineBreak();
    
        while (!isComplete) {
    
            String line = in.nextLine();
            String firstWord = Parser.getFirstWord(line);
    
            switch (firstWord) {
            case "mark":
                TaskManager.markTask(Parser.getTaskNumber(line));
                break;
            case "unmark":
                TaskManager.unmarkTask(Parser.getTaskNumber(line));
                break;
            case "delete":
                int taskNumberDelete = Parser.getTaskNumber(line);
                TaskManager.deleteTask(taskNumberDelete);
                break;
            case "list":
                TaskManager.listTask();
                break;
            case "todo":
                Parser.getTodoDetails(line);
                break;
            case "deadline":
                Parser.getDeadlineDetails(line);
                break;
            case "event":
                Parser.getEventDetails(line);
                break;
            case "find":
                TaskManager.findTask(Parser.getTaskDescription(line));
                break;
            case "bye":
                isComplete = true;
                break;
            default:
                System.out.println("Unrecognized command. Please try again.");
                break;
            }
    
            Ui.printLineBreak();
        }
    
        printBye();
        in.close();
    }
    
    /**
     * Prints the welcome message
     */
    private static void printHello() {
        System.out.println("________         __         __________");
        System.out.println("\\______ \\  __ __|  | __ ____\\______   \\__ __  ____   ____   ___________ ");
        System.out.println(" |    |  \\|  |  \\  |/ // __ \\|       _/  |  \\/    \\ /    \\_/ __ \\_  __ \\ ");
        System.out.println(" |    `   \\  |  /    <\\  ___/|    |   \\  |  /   |  \\   |  \\  ___/|  | \\/ ");
        System.out.println("/_______  /____/|__|_ \\\\___  >____|_  /____/|___|  /___|  /\\___  >__| ");
        System.out.println("        \\/           \\/    \\/       \\/           \\/     \\/     \\/ ");
        System.out.println("Hello! I'm DukeRunner\nWhat can I do for you?\n");
    }

    /**
     * Prints the goodbye message
     */
    private static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a line break
     */
    private static void printLineBreak() {
        System.out.println("==============================\n");
    }

}
