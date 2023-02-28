package ui;
public class UI {
    private static final String LINE = "____________________________________________________________";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void greet() {
        System.out.println(logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println(LINE);
    }

    public static void welcome() {
        System.out.println(LINE);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public static void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void writeSaveDataERROR() {
        System.out.println(LINE);
        System.out.println("WRITE SAVE DATA ERROR");
        System.out.println(LINE);
    }

    public static void loadSaveDataERROR() {
        System.out.println(LINE);
        System.out.println("LOAD SAVE DATA ERROR");
        System.out.println(LINE);
    }
}
