package ui;

/**
 * User interaction of Duke by printing out the respective messages
 */
public class UI {
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREET = "Hello! I'm Duke";
    private static final String START = "What can I do for you?";
    private static final String BYE = "Bye. Hope to see you again soon!";
    private static final String WRITE_DATA_ERROR = "WRITE SAVE DATA ERROR";
    private static final String LOAD_DATA_ERROR = "LOAD SAVE DATA ERROR";

    /**
     * Print out the logo and greet the user
     */
    public static void greet() {
        System.out.println(LOGO);
        System.out.println(LINE);
        System.out.println(GREET);
        System.out.println(LINE);
    }

    public static void start() {
        System.out.println(LINE);
        System.out.println(START);
        System.out.println(LINE);
    }

    public static void bye() {
        System.out.println(LINE);
        System.out.println(BYE);
        System.out.println(LINE);
    }

    public static void writeSaveDataERROR() {
        System.out.println(LINE);
        System.out.println(WRITE_DATA_ERROR);
        System.out.println(LINE);
    }

    public static void loadSaveDataERROR() {
        System.out.println(LINE);
        System.out.println(LOAD_DATA_ERROR);
        System.out.println(LINE);
    }
}
