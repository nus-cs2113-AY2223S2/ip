// This class handles the user interface of the program

//import required packages
import java.util.ArrayList;

public class Ui {
    //method that prints the welcome message
    public static void printWelcomeMessage(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    //method that prints the goodbye message
    public static void printGoodbyeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    //method that prints the list of tasks
    public static void printTaskList(ArrayList<Task> tasks){
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i<tasks.size();i++){
            System.out.println((i+1) + "." + tasks.get(i));
        }
    }
    //method that prints the task that has been added
    public static void printAddedTask(Task task, int taskCount){
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    //method that prints the task that has been marked as done
    public static void printDoneTask(Task task){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }
    //method that prints the task that has been marked as undone
    public static void printUndoneTask(Task task){
        System.out.println("Noted. I've marked this task as undone:");
        System.out.println(task);
    }
    //method that prints the task that has been deleted
    public static void printDeletedTask(Task task, int taskCount){
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    //method that prints the tasks that match the keyword
    public static void printMatchingTasks(ArrayList<Task> tasks, int taskCount){
        System.out.println("Here are the matching tasks in your list:");
        for(int i=0;i<taskCount;i++){
            System.out.println((i+1) + "." + tasks.get(i));
        }
    }
    //method that prints the error message
    public static void printErrorMessage(String errorMessage){
        System.out.println(errorMessage);
    }
    
}
