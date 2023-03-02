package Duke;
import Duke.Tasks.Task;

import java.util.Scanner;

public class Ui {
    public static Scanner in = new Scanner(System.in);
    public static void printHelp(){
        System.out.println("--------------------------------");
        System.out.println("Enter \"todo task name\" to add a task.");
        System.out.println("Enter \"event /from start time /to end time\" to add an event.");
        System.out.println("Enter \"deadline task name /by end time\" to add a deadline");
        System.out.println("Enter \"mark index\"to mark a task as done.");
        System.out.println("Enter \"unmark index\"to mark a task as not done.");
        System.out.println("Enter \"delete index\"to delete a task.");
        System.out.println("Enter \"list\" to print all the tasks.");
        System.out.println("Enter \"bye\" to terminate this program.");

    }
    public static void printList(Task[] lists,int index){
        System.out.println("list:");
        for (int i = 0; i < index; i++) {
            System.out.println("\t" + (i + 1) + "." + lists[i].toString());
        }
    }
    public static void printError(){
        System.out.println("Sorry, I don't get your message.");
    }
    public static String initializeLine(){
        in=new Scanner(System.in);
        return in.nextLine();
    }
    public static String nextLine(){
        return in.nextLine();
    }
    public static void welcomeMessage(){
        System.out.println("--------------------------------");
        System.out.println("Hello! I'm Duke.Duke");
        System.out.println("What can I do for you?");
        System.out.println("Enter \"help\" if you want to see the command.");
        System.out.println("--------------------------------");
    }
    public static void goodbyeMessage(){
        System.out.println("--------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------");
    }
}
