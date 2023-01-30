package duke.commands;

public class DukeException extends Exception{

    /**
     * Prints an error message when the user tries to input an invalid command
     */
    public static void printError(){
        System.out.println("Please enter a valid command!\n");
    }

    /**
     * Prints an error message when the user tries to mark a task that does not exist
     */
    public static void emptyList() {
        System.out.println("Your list is empty!\n" + UI.getLineBreak());
    }
}
