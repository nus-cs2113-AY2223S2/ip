package Duke;

import Duke.Tasks.Task;

import java.util.ArrayList;

public class Ui {
    static final String LINE = "____________________________________________________________";
    static final String BLANK = "    ";

    /**
     * Prints run message when by is called
     */
    static void printWelcome() {
        String logo = BLANK + LINE + "\n"
                + BLANK + "Hello! I'm Duke.Duke\n"
                + BLANK + "What can I do for you?\n"
                + BLANK + LINE;
        System.out.println("\n" + logo);
    }


    /**
     * Prints goodbye message when by is called
     */
    static void doExit() {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Bye. Hope to see you again soon!");
        System.out.println(BLANK + LINE);
    }
}
