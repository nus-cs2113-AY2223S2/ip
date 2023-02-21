/**
 * This class handles the user interface of the program.
 * It contains methods to print the welcome message, goodbye message, list of tasks, added task, deleted task, and marked task as done.
 * It also contains a scanner object to read the user input.
 * @param scan the scanner object to read the user input
 * @param printWelcomeMessage method to print the welcome message
 * @param printGoodbyeMessage method to print the goodbye message
 * @param printTaskList method to print the list of tasks
 * @param printAddedTask method to print the added task
 * @param printDeletedTask method to print the deleted task
 * @param printDoneTask method to print the marked task as done
 * @throws DukeException if the deadline is not in the correct format
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    //scanner object
    private Scanner scan;
    //constructor that creates a new scanner object
    public Ui(){
        scan = new Scanner(System.in);
    }

    /**
     * This method prints the welcome message.
     */
    public static void printWelcomeMessage(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    
    /**
     * This method prints the goodbye message.
     */
    public static void printGoodbyeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * This method prints the list of tasks.
     * @param tasks the list of tasks
     */
    public static void printTaskList(ArrayList<Task> tasks){
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i<tasks.size();i++){
            System.out.println((i+1) + "." + tasks.get(i));
        }
    }

    /**
     * This method prints the added task.
     * @param task the task that has been added
     */
    public static void printAddedTask(Task task, int taskCount){
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    
    /**
     * This method prints the marked task as done.
     * @param task the task that has been marked as done
     */
    public static void printDoneTask(Task task){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * This method prints the marked task as undone.
     * @param task the task that has been marked as undone
     */
    public static void printUndoneTask(Task task){
        System.out.println("Noted. I've marked this task as undone:");
        System.out.println(task);
    }

    /**
     * This method prints the deleted task.
     * @param task the task that has been deleted
     * @param taskCount the number of tasks in the task list
     */
    public static void printDeletedTask(Task task, int taskCount){
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * This method prints the matching tasks.
     * @param tasks the list of matching tasks
     * @param taskCount the number of tasks in the task list
     */
    public static void printMatchingTasks(ArrayList<Task> tasks, int taskCount){
        System.out.println("Here are the matching tasks in your list:");
        for(int i=0;i<taskCount;i++){
            System.out.println((i+1) + "." + tasks.get(i));
        }
    }

    /**
     * This method prints the error message.
     * @param errorMessage the error message
     */
    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    /**
     * This method prints the error message.
     * @param errorMessage the error message
     */
    public static void printErrorMessage(String errorMessage){
        System.out.println(errorMessage);
    }

    /**
     * This method reads the user input.
     * @return the user input
     */
    public String readCommand(){
        return scan.nextLine();
    }

    
}
