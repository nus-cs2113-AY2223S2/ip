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

        String command;
        Scanner in = new Scanner(System.in);
        String commands[] = new String[100];

        int i = 0;

        while (i < 100) {

            command = in.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            else if (command.equals("list")) {

                for (int j = 0; j < i; j += 1) {
                    System.out.println(j+1 + ". " + commands[j]);
                }


            }

            else {
                commands[i] = command;
                System.out.println("added: " + commands[i]);
                i += 1;
            }
        }

    }
}
