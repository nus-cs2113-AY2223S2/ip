package tusky.ui;

import tusky.constants.Messages;
import tusky.exceptions.EmptyDescriptionException;
import tusky.tasks.Task;
import tusky.tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles all interactions with the user
 */
public class Ui {
    Scanner in;
    public Ui(){
        in = new Scanner(System.in);
    }

    /**
     * Reads the line of input from the user
     * @return The line of input from the user
     */
    public String readCommand(){
        return in.nextLine();
    }

    /**
     * Wrapper around println to prefix the output with a large indent
     * @param x The string to be printed
     */
    public void println (String x) {
        System.out.println(Messages.LARGE_INDENT + x);
    }

    /**
     * Wrapper around printf to prefix the output with a large indent
     * @param format The format of the string to be printed
     * @param args The arguments to be formatted
     */
    public void printf (String format, Object... args) {
        System.out.print(Messages.LARGE_INDENT);
        System.out.printf(format, args);
    }
    public void showLine () {
        println(Messages.LINE.toString());
    }

    public void showWelcome(){
        println(Messages.WELCOME.toString());
    }

    public void showGoodbye(){
        println(Messages.GOODBYE.toString());
    }
    public void showFileLoaded(){
        println(Messages.FILE_LOADED.toString());
    }

    public void showFileCreated(){
        println(Messages.FILE_CREATED.toString());
    }

    public void showAddTask (Task task, int size) {
        printf(Messages.TASK_ADDED.toString());
        printf(Messages.SMALL_INDENT + "%s\n", task.getDetailedString());
        printf(Messages.TASK_COUNT.toString(), size);
    }

    public void showListTasks (TaskList tasks) {
        println(Messages.TASK_LIST.toString());
        for (int i = 1; i <= tasks.size(); i++) {
            printf(" %d.%s\n", i, tasks.getTask(i-1).getDetailedString());
        }
    }

    public void showMarkTask (Task task) {
        println(Messages.TASK_MARKED.toString());
        printf(Messages.SMALL_INDENT + "%s\n", task.getDetailedString());
    }

    public void showUnmarkTask (Task task) {
        println(Messages.TASK_UNMARKED.toString());
        printf(Messages.SMALL_INDENT + "%s\n", task.getDetailedString());
    }

    public void showDeleteTask (Task task, int size) {
        println(Messages.TASK_DELETED.toString());
        printf(Messages.SMALL_INDENT + "%s\n", task.getDetailedString());
        printf(Messages.TASK_COUNT.toString(), size);
    }

    public void showFoundTasks (ArrayList<Task> tasks) {
        println(Messages.TASK_FIND.toString());
        for (int i = 1; i <= tasks.size(); i++) {
            printf(" %d.%s\n", i, tasks.get(i-1).getDetailedString());
        }
    }

    // Errors
    public void showEmptyDescription (EmptyDescriptionException e) {
        printf(e.getMessage());
    }

    public void showUnknownCommand () {
        println(Messages.ERR_UNKNOWN_COMMAND.toString());
    }
    public void showInvalidIndex () {
        println(Messages.ERR_INVALID_INDEX.toString());
    }

    public void showInvalidParameters () {
        println(Messages.ERR_INVALID_PARAMETERS.toString());
    }

    public void showKeyNotFound (String message) {
        println(message);
    }
    public void showUnknownException (Exception e) {
        println(Messages.ERR_UNKNOWN_EXCEPTION + e.getMessage());
        e.printStackTrace();
    }

    public void showInvalidDate(String description){
        printf(Messages.ERR_INVALID_DATE.toString(), description);
    }
    public void showFileLoadError(){
        println(Messages.ERR_LOADING_FILE.toString());
    }
    public void showNumberFormatError(){
        println(Messages.ERR_NUMBER_FORMAT.toString());
    }

    @Override
    public String toString () {
        return "Ui{}";
    }
}
