package Tasks;

import Ui.Ui;
import Commands.Command;
import Exception.RolexException;
import java.util.ArrayList;

public class Task {

    protected String taskSign;
    protected String taskName;
    protected boolean isDone;

    public void markTask(){
        Ui.printLines();
        if(this.isDone){
            System.out.println("Task is already marked.");
        } else{
            this.isDone = true;
            System.out.println("Well Done. This task is marked as done:");
            System.out.println(this);
        }
        Ui.printLines();
    }

    public void unMarkTask(){
        Ui.printLines();
        if(this.isDone){
            this.isDone = false;
            System.out.println("Oh no, I've unmarked this task as it is not done:");
            System.out.println(this);
        } else{
            System.out.println("Task is already unmarked.");
        }
        Ui.printLines();
    }

    public static void addPrintTask(Task newTask, ArrayList<Task> task) {
        Ui.printLines();
        task.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.get(task.size() - 1));
        System.out.println("\nNow you have " + task.size() + " tasks in the list.");
        Ui.printLines();
    }

    public static void processCommand(String userInput){

        if(userInput.startsWith("list")){
            Command.inputIsList();
        } else if(userInput.startsWith("mark")){
            Command.inputIsMark(userInput);
        } else if(userInput.startsWith("unmark")){
            Command.inputIsUnmark(userInput);
        } else if(userInput.startsWith("todo")){
            Command.inputIsTodo(userInput);
        } else if(userInput.startsWith("deadline")){
            Command.inputIsDeadline(userInput);
        } else if(userInput.startsWith("event")){
            Command.inputIsEvent(userInput);
        } else if(userInput.startsWith("delete")) {
            Command.inputIsDelete(userInput);
        } else{
            RolexException.detectError(userInput);
        }
    }

    public Task(String taskName, boolean test, String taskSign){
        this.taskName = taskName;
        this.taskSign = taskSign;
        this.isDone = test;
    }

    public void deleteTask(int indexToDelete, int taskSize){
        Ui.printLines();
        System.out.println("Noted. I've removed this task.");
        System.out.println(this);
        System.out.println("\nNow you have " + (taskSize-1) + " tasks in the list.");
        Ui.printLines();
    }

    public String giveTaskSign(){
        return this.taskSign;
    }

    public String giveTaskName(){
        return this.taskName;
    }

    public boolean giveTaskStatus(){
        return this.isDone;
    }

    public String taskStatus(){
        return (this.giveTaskStatus() ? "X" : " ");
    }

    public String toString(){
        return this.giveTaskSign() + "[" + this.taskStatus() + "] " + this.giveTaskName();
    }

}