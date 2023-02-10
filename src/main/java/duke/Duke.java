package duke;

import java.util.Scanner;

import parser.IParser;
import parser.Parser;
import task.ITaskController;
import task.TaskController;
import ui.IUi;
import ui.Ui;

/**
 * Duke class acts as the overall controller for the other classes. It will use UI class
 * for user interfacing, and parser to make sense of the user command given.
 */
public class Duke {
    // Composition of other classes to support main method
    private static Scanner sc = new Scanner(System.in);
    private static IParser parser = new Parser(sc);
    private static ITaskController taskController = new TaskController();
    private static IUi ui = new Ui();
    /**
     * Main program that runs the Duke program.
     * Greets users and exits.
     * @param args
     */
    public static void main(String[] args) {
        ui.greet();
        boolean isExit = false;
        do {
            isExit = executeProgram();
        } while(!isExit);
        ui.bye();
    }
    /**
     * Executes the logic of the program. Returns true when program should exit.
     * @return boolean isExit
     */
    private static boolean executeProgram(){
        try {
            switch (parser.getCommand()) {
            case EXIT:
                return true;
            case LIST:
                ui.printSystemMessage(taskController.getTasks());
                return false;
            case TASK:
                ui.printSystemMessage(taskController.addTask(parser.getTask()));
                return false;
            case UNMARK:
                ui.printSystemMessage(taskController.unmarkTask(parser.getTaskIndex()));
                return false;
            case MARK:
                ui.printSystemMessage(taskController.markTask(parser.getTaskIndex()));
                return false;
            case DELETE:
                ui.printSystemMessage(taskController.deleteTask(parser.getTaskIndex()));
            default:
                return false;
            }
        } catch (DukeException e) {
            ui.printSystemErrorMessage(e.getMessage());
            return false;
        } 
    }
}