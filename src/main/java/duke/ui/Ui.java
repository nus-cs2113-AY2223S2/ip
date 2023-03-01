package duke.ui;

public class Ui {

    //prints border
    public static void printBorder() {
        String border = "____________________________________________________________ \n";
        System.out.println(border);
    }

    //prints greeting
    public static void printGreeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        String border = "____________________________________________________________ \n";
        String greeting = "Hello! I'm duke.\n" + "What can I do for you?\n";
        System.out.println(logo);
        printBorder();
        System.out.println(greeting);
        printBorder();
    }

    //prints exit statement
    public static void printExit(){
        String exit = "Bye. Hope to see you again soon! \n";
        printBorder();
        System.out.println(exit);
        printBorder();
    }
}
