package duke;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import parser.IParser;
import parser.Parser;
import serialiser.ISerialiser;
import serialiser.Serialiser;
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
    private static ISerialiser serialiser = null;
    /**
     * Main program that runs the Duke program.
     * Greets users and exits.
     * @param args
     */
    public static void main(String[] args) {
        loadDataFile();
        ui.greet();
        boolean isExit = false;
        do {
            isExit = executeProgram();
        } while(!isExit);
        saveDataFile();
        ui.bye();
    }
    private static void loadDataFile(){
        while (serialiser == null) {
            try {
                serialiser = new Serialiser();
            } catch (IOException e) {
                checkDataDirectory();
            }
        }
        serialiser.deserialiseFile(taskController);
    }
    private static void saveDataFile() {
        serialiser.serialiseFile(taskController);
    }
    private static void checkDataDirectory() {
        File directory = new File("data");
        if (!directory.isDirectory()) {
            // Then directory does not exist and need to create one
            ui.printSystemErrorMessage("The data directory does not exist yet!!\nProceeding to create one now...");
            directory.mkdir();
            checkDataFile();
            ui.printSystemMessage("Directory creation success");
        }
    }
    private static void checkDataFile() {
        try {
            File file = new File("data/duke.txt");
            if (!file.isFile()) {
                // Then directory does not exist and need to create one
                ui.printSystemErrorMessage("The data file does not exist yet!!\nProceeding to create one now...");
                file.createNewFile();
                ui.printSystemMessage("file creation success");
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
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
            case FIND:
                ui.printSystemMessage(taskController.findTask(parser.getFindKeyword()));
                return false;
            case DELETE:
                ui.printSystemMessage(taskController.deleteTask(parser.getTaskIndex()));
                return false;
            default:
                return false;
            }
        } catch (DukeException e) {
            ui.printSystemErrorMessage(e.getMessage());
            return false;
        } 
    }
}