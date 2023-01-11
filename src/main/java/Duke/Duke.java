package Duke;
public class Duke {
    static String LINEBREAK = "________________________________________\n";
    /**
     * Main program that runs the Duke program.
     * Greets users and exits.
     * @param args
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printMessage(logo);

        // Greet user
        printMessage("Hello! I'm Duke\nWhat can I do for you?");
        
        // Exit the program
        printMessage("Bye. Hope to see you again soon!");
    }
    /**
     * Prints message followed by a linebreak
     * @param message Output message to print
     */
    private static void printMessage(String message){
        System.out.println(message);
        System.out.println(LINEBREAK);
    }
}
