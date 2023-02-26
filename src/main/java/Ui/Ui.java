package Ui;

import java.util.Scanner;

public class Ui {

    public static void printLines(){
        System.out.println("--------------------------------------------------");
    }

    /**
     * This function takes in the user input and returns the next line.
     * @return user input
     */
    public static String takeUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }


    /**
     * Rolex greets user at the start of the program.
     */
    public static void rolexGreetsUser() {
        printLines();
        System.out.println("Hello! I'm ROLEX");
        System.out.println("What can I do for you?");
        printLines();
    }


    /**
     * Rolex says bye to the user at the end of the program.
     */
    public static void rolexSaysBye() {
        printLines();
        System.out.println("Bye. Hope to see you again soon!");
        printLines();
    }


    /**
     * This method is designed to display error message.
     */
    public static void printInvalidNumber(){
        printLines();
        System.out.println("Invalid index. Please enter valid index number!");
        printLines();
    }

}
