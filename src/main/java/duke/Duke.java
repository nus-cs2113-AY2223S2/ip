package duke;

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

    private static void parseResponse(String response, List list) {


        if (response.equals("list")) {
            printSeperator();
            list.listDisplay();
            printSeperator();
        } else if (response.length() >= 5 && response.substring(0, 5).equals("mark ")) {
            printSeperator();
            list.markTask(response.substring(5));
            printSeperator();
        } else if (response.length() >= 7 && response.substring(0, 7).equals("unmark ")) {
            printSeperator();
            list.unmarkTask(response.substring(7));
            printSeperator();
        } else { // add task
            printSeperator();
            list.listAdd(response);
            printSeperator();
        }
    }

    public static void main(String[] args) {

        greet();
        List list = new List();

        Scanner in = new Scanner(System.in);
        String response = in.nextLine();

        while (!response.equals("bye")) {
            parseResponse(response, list);
            response = in.nextLine();
        }
        // response is bye.
        farewell();

    }
}
