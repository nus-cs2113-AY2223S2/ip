package duke.functionalities;

import duke.exception.DukeException;
import duke.tasks.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This Class deals with Interactions with the User
 * */
public class Ui {

    /**
     * Prints File Loading Error Message to the User
     * */
    public void showLoadingError() {
        System.out.println(" Sorry, there is an error in loading the file!");
    }

    /**
     * Prints the Divider Line
     * */
    public void showLine() {
        String dividerLine = "______________________________________________________________________";
        System.out.println(dividerLine);
    }

    /**
     * Prints Welcome Message to the User
     * */
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.print(" Hello from\n" + logo);
        showLine();
        System.out.println(" Hello! I'm Duke, your personal task manager.");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /**
     * Reads in the command entered by the User
     *
     * @return The Command entered by the User
     * */
    public String readUserCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Prints Error Message to the User
     * */
    public void showErrorMessage() {
        System.out.println(" Sorry, an error was encountered! Here is the error description:");
        DukeException.showError();
    }

    /**
     * Prints the Duke Data File Creation Status to the User
     *
     * @param b The File Status (True/False)
     * */
    public void showStatus(boolean b) {
        if (b) {
            System.out.println(" The Duke Data file has been created!");
        } else {
            System.out.println(" The Duke Data file has not been created!");
        }
    }

    /**
     * Prints the Path of Duke Data File if it exists in the System
     *
     * @param f The Duke Data File
     * */
    public void showFileExists(File f) {
        System.out.println(" The Duke Data file stored in " + f + " exists!");
    }

    /**
     * Prints Error Message pertaining to File Storage Operations to the User
     *
     * @param e IOException
     * */
    public void showSomethingWentWrong(IOException e) {
        System.out.println(" Something went wrong: " + e.getMessage());
    }

    /**
     * Prints the no of Items in Task List to the User
     *
     * @param taskCount The no of Tasks in the List
     * */
    public void showTaskCount(int taskCount) {
        System.out.println(" Now you have " + taskCount + " tasks in your list.");
    }

    /**
     * Prints Task Added Message to the User
     *
     * @param tasks The Task ArrayList
     * @param taskIndex The index of the Task in the Task ArrayList
     * */
    public void showTaskAdded(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(" Got it. I've added this task:");
        System.out.println(tasks.get(taskIndex).toString());
        showTaskCount(tasks.size());
    }

    /**
     * Prints Task Deleted Message to the User
     *
     * @param tasks The Task ArrayList
     * @param description The Task Description
     * */
    public void showTaskDeleted(ArrayList<Task> tasks, String description) {
        System.out.println(" Noted. I've deleted this task:");
        System.out.println(description);
        int taskCount = tasks.size() - 1;
        showTaskCount(taskCount);
    }

    /**
     * Prints a Message to the User
     *
     * @param s The Message to be displayed to the User
     * */
    public void showUserMessage(String s) {
        System.out.println(s);
    }
}