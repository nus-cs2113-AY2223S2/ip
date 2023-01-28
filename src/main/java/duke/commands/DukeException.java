package duke.commands;

public class DukeException extends Exception{
    public static void printError(){
        System.out.println("Please enter a valid command!\n");
    }

    public static void emptyList() {
        System.out.println("Your list is empty!\n" + UI.getLineBreak());
    }
}
