package duke.functionalities;

import duke.exception.DukeException;
import duke.tasks.Task;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public void showLoadingError() {
        System.out.println(" Sorry, there is an error in loading the file!");
    }

    public void showLine() {
        String dividerLine = "______________________________________________________________________";
        System.out.println(dividerLine);
    }

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

    public String readUserCommand() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        return command;
    }

    public void showErrorMessage() {
        System.out.println(" Sorry, an error was encountered! Here is the error description:");
        DukeException.showError();
    }

    public void showStatus(boolean b) {
        if (b) {
            System.out.println(" The Duke Data file has been created!");
        } else {
            System.out.println(" The Duke Data file has not been created!");
        }
    }

    public void showFileExists(File f) {
        System.out.println(" The Duke Data file stored in " + f + " exists!");
    }

    public void showSomethingWentWrong(IOException e) {
        System.out.println(" Something went wrong: " + e.getMessage());
    }

    public void showTaskCount(int taskCount) {
        System.out.println(" Now you have " + taskCount + " tasks in your list.");
    }

    public void showTaskAdded(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(" Got it. I've added this task:");
        System.out.println(tasks.get(taskIndex).toString());
        showTaskCount(tasks.size());
    }

    public void showTaskDeleted(ArrayList<Task> tasks, String description) {
        System.out.println(" Noted. I've deleted this task:");
        System.out.println(description);
        int taskCount = tasks.size() - 1;
        showTaskCount(taskCount);
    }

    public void showUserMessage(String s) {
        System.out.println(s);
    }
}