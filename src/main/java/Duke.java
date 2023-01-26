import java.lang.reflect.Array;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String line = "____________________________________________________________\n";
        String greet = line +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                line;
        System.out.println(greet);

        // Level 1
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(line + userInput + "\n" + line);
            userInput = in.nextLine();
        }
        System.out.println("Thank you for using Duke. Hope to see you soon!");

    }
}
