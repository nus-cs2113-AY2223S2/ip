package util;

import java.util.Scanner;
import java.util.ArrayList;
import util.Task;

public class Ui {
    public Ui() {
    }
    
    public void displayCommandsList(ArrayList<Task> commands) {
        int i = 1;
        System.out.println("\t_____________________________________________________");
        for (Task task : commands) {
            System.out.println("\t" + i + "." + task);
            i++;
        }
        System.out.println("\t_____________________________________________________");
    }

    public String ask() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public void printDashLine() {
        System.out.println("\t_____________________________________________________");
    }

    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" Hello! I'm Duke ");
        System.out.println(" What can I do for you? \n");
        System.out.println("____________________________________________________________\n");
    }

    public void printByeMessage() {
        System.out.println("____________________________________________________________\n");
        System.out.println("\t Bye. Hope to see you again soon!\n");
        System.out.println("____________________________________________________________\n");
    }

    public void printSpecificTask(int index, ArrayList<Task> commands, String message) {
        if (!message.equals("")) {
            System.out.println("\t " + message);
        }
        System.out.println("\t" + index + "." + commands.get(index - 1));
    }

    public void printLenghtOfTaskList(ArrayList<Task> commands) {
        System.out.println("\t Now you have " + commands.size() + " tasks in the list.");
    }

    public void printSearchResult(ArrayList<Integer> relatedIndexes,ArrayList<Task> commands){
        printDashLine();
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i:relatedIndexes){
            printSpecificTask(i+1, commands, "");
        }
        printDashLine();
    }
}
