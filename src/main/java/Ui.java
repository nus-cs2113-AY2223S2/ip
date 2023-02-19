/**
 * Represents messages printed by Duke for interactions with the user.
 */
public class Ui {
    /**
     * Prints a horizontal line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Greets the user at the start of the program.
     */
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Bids the user farewell when they exit the program.
     */
    public void sayBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printLine();
    }
}
