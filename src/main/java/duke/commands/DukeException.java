package duke.commands;

public class DukeException extends Exception{
    public static void printError(){
        System.out.println("Please enter a valid command!\n");
    }
}
