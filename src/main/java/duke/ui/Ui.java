package duke.ui;

public abstract class Ui {
    public static String BREAK_LINE = "\t-------------------------------------------------------------------\n";

    /* print greet messages from Duke */
    public static void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println(BREAK_LINE
                + "\tHello! I'm Duke :)\n"
                + "\tWhat can I do for you?\n"
                + BREAK_LINE);
    }

    public static void printLoadDataComplete() {
        System.out.print("\tLoading Complete :)\n" + BREAK_LINE);
    }

    public static void printExit() {
        System.out.print(BREAK_LINE + "\tBa-bye. Hope to see you again soon :)\n" + BREAK_LINE);
    }
}
