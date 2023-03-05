package duke;

/*
    Contains the methods to display the greeting when the program start
 */

public class Ui {
    public static final String LINE = "    ____________________________________________________________";

    /**
     * For greeting
     */
    public static void greeting() {

        System.out.println(LINE);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
    }


    public static void showNotFoundError(){
        System.out.println("Failed to locate the file");
    }
}
