package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles interaction with the user
 */
public class UI {
    public static void printGreeting(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printFarewell(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static String getUserInput(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void printToUser(String message){
        System.out.println(message);
    }

    /**
     * Formats and prints the list of tasks passed in to the user
     *
     * @param tasks The list of tasks to print
     * @param header The header to print before the list of tasks
     */
    public static void printTaskList(ArrayList<Task> tasks, String header){
        String stringToPrint = "";
        stringToPrint = stringToPrint.concat(header);
        for(int i = 0; i < tasks.size(); ++i) {
            stringToPrint = stringToPrint.concat("\n" + Integer.toString(i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(stringToPrint);
    }
}
