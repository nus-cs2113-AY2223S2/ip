// Java program to read data of various types using Scanner class.
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Duke, ready to serve you\n" +
                " What can I do for my master?\n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMessage);

        // Declare the object and initialize with
        // predefined standard input object
        Scanner sc = new Scanner(System.in);

        // String input
        String input = "";

        while(true) {
            // Scan the input
            input = sc.nextLine();

            // Check if it read
//            if (input.contains("read")) {
//                System.out.println("ada");
//            }

            // Create Text Line
            String textLine = "\n\n____________________________________________________________\n\n";
            // Prints output
            if (input.equals("bye")) {
                String byeText = "Bye. Hope to see you again soon!";
                System.out.println(textLine + byeText + textLine);
                break;
            }
            System.out.println(textLine + input + textLine);
        }




    }
}
