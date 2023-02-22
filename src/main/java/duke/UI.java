package duke;

import java.util.Scanner;

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
}
