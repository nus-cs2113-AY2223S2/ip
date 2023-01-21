import java.util.Scanner;

public class Duke {

    public static void printSeperator() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        printSeperator();
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?");
        printSeperator();
        System.out.println("");
    }



    public static void farewell() {
        printSeperator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeperator();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        greet();
        List list = new List();

        Scanner in = new Scanner(System.in);
        String response = in.nextLine();

        while (!response.equals("bye")) {
            if (response.equals("list"))
            {
                printSeperator();
                list.listDisplay();
                printSeperator();
            }
            else {
                printSeperator();
                list.listAdd(response);
                printSeperator();
            }
            response = in.nextLine();
        }
        // response is bye.
        farewell();

    }
}
