package duke.ui;

public class Ui {

    public static final String LINE_BREAK = "____________________________________________________________";

    public void greet() {
        System.out.println(LINE_BREAK);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }

}
