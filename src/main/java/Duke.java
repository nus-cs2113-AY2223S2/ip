import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String command = "";
        Scanner in = new Scanner(System.in);

        int textNum = 0;                            // number of texts stored
        String[] storedTexts = new String[100];     // array of stored texts
        while (!command.equals("bye")) {
            command = in.nextLine();
            if (!command.equals("bye") && !command.equals("list")) {
                System.out.println("added: " + command);
                storedTexts[textNum] = command;
                textNum++;
            } else if (command.equals("list")) {
                for(int i = 0; i < textNum; i++)
                {
                    System.out.println((i + 1) + ". " + storedTexts[i]);
                }
            }

        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
