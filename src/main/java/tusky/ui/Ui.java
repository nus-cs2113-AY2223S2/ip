package tusky.ui;

import tusky.constants.Messages;
import tusky.exceptions.EmptyDescriptionException;
import tusky.tasks.Task;
import tusky.tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner in;
    public Ui(){
        in = new Scanner(System.in);
    }

    public String readCommand(){
        return in.nextLine();
    }
    // println adds indentation before each println output string
    public void println (String x) {
        System.out.println(Messages.LARGE_INDENT + x);
    }

    // printf adds indentation before each printf output string
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
        println(Messages.TASK_LIST.toString());
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

    @Override
    public String toString () {
        return "Ui{}";
    }
}
