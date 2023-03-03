package Tasks;

import Ui.Ui;
import Commands.Command;
import Exception.RolexException;
import java.util.ArrayList;

public class Task {

    protected String taskSign;

    protected String taskName;

    protected boolean isDone;


    /**
     * This is the default constructor of the class.
     *
     * @param taskName: name of task
     * @param test: status of task
     * @param taskSign: sign of task(eg: [T], [D], [E])
     */
    public Task(String taskName, boolean test, String taskSign){
        this.taskName = taskName;
        this.taskSign = taskSign;
        this.isDone = test;
    }


    /**
     * This method is designed to delete a task when given its index number in
     * the task list.
     *
     * @param indexToDelete: index number of task in task list
     * @param taskSize: size of task list(ArrayList)
     */
    public void deleteTask(int indexToDelete, int taskSize){
        Ui.printLines();
        System.out.println("Noted. I've removed this task.");
        System.out.println(this);
        System.out.println("\nNow you have " + (taskSize-1) + " tasks in the list.");
        Ui.printLines();
    }


    /**
     * This method is designed to process the user input and call the respective
     * methods from other classes.
     *
     * @param userInput: input entered by user
     */
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
        } else if(userInput.startsWith("find")){
            Command.inputIsFind(userInput);
        } else{
            RolexException.detectError(userInput);
        }
    }


    /**
     * This in-class method is designed to mark a task.
     */
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


    /**
     * This in-class method is designed to mark a task.
     */
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


    /**
     * This in-class method is designed add a new task and display
     * confirmation message.
     */
    public static void addPrintTask(Task newTask, ArrayList<Task> task) {
        Ui.printLines();
        task.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.get(task.size() - 1));
        System.out.println("\nNow you have " + task.size() + " tasks in the list.");
        Ui.printLines();
    }


    /**
     * This is a getter for getting task sign.
     *
     * @return task sign
     */
    public String giveTaskSign(){
        return this.taskSign;
    }


    /**
     * This is a getter for getting task name.
     *
     * @return task name
     */
    public String giveTaskName(){
        return this.taskName;
    }


    /**
     * This is a getter for getting task status.
     *
     * @return task status
     */
    public boolean giveTaskStatus(){
        return this.isDone;
    }


    /**
     * This in-class method is designed to check if a task is marked
     * or unmarked.
     *
     * @return "X" if true, " " if false
     */
    public String taskStatus(){
        return (this.giveTaskStatus() ? "X" : " ");
    }


    /**
     * This is an override method with 3 overrides from 3 different subclasses: Todo, Event, Deadline
     * The purpose of this method is to display the contents of the task.
     *
     * @return task sign + task status + task name as a string
     */
    public String toString(){
        return this.giveTaskSign() + "[" + this.taskStatus() + "] " + this.giveTaskName();
    }

}