package Commands;

import static Commands.Text.line;

public class error {

    public static void emptyDescription(){
        System.out.println("Please do not leave the description empty!");
        System.out.println(line);
    }
    public static void invalidCommand(){
        System.out.println("Please enter a valid command!");
        System.out.println(line);
    }

    public static void indexNotFound(){
        System.out.println("Please ensure the number you have entered is within the list!");
        System.out.println(line);
    }

    public static void listIsEmpty(){
        System.out.println("Invalid command! The list is empty!");
        System.out.println(line);
    }

}
