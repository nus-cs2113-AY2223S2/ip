import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printAdd(String prompt) {
        System.out.println("    ____________________________________________________________\n" +
                "     added: " + prompt + "\n" +
                "    ____________________________________________________________\n");
    }

    public static void printList(String[] prompt) {
        System.out.println("    ____________________________________________________________");
        int count = 1;
        for (String item : prompt) {
            System.out.println("     " + count + ". " + item);
            count++;
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {

        String greet = "____________________________________________________________\n" +
                " Hello! I'm kimo\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greet);

        String[] userList = new String[100];
        String userInput = "";
        int listLength = 0;
        Scanner in = new Scanner(System.in);

        inputLoop:
        while (true) {
            userInput = in.nextLine();
            switch (userInput) {
            case "list":
                printList(Arrays.copyOf(userList, listLength));
                break;

            case "bye":
                break inputLoop;

            default:
                userList[listLength] = userInput;
                listLength++;
                printAdd(userInput);
            }
        }

        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }
}

        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
