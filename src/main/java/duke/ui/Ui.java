package duke.ui;

/**
 * Class triggers and ends interactions with user.
 */
public class Ui {

    /**
     * Prints startup message.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints goodbye message.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
