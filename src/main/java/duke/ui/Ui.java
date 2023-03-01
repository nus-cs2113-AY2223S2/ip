package duke.ui;

public class Ui {
    public static void printBorder() {
        String border = "____________________________________________________________ \n";
        System.out.println(border);
    }

    public static void printGreeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm duke.\n" + "What can I do for you?\n";
        System.out.println(logo);
        printBorder();
        System.out.println(greeting);
        printBorder();
    }

    public static void printExit(){
        String exit = "Bye. Hope to see you again soon! \n";
        printBorder();
        System.out.println(exit);
        printBorder();
    }
}
