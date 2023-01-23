import java.util.Scanner;

public class Duke {
    public static void echoInstruction(String command, String[] list, int index) {
        System.out.println("    ____________________________________________________________\n");
        if (command.equals("bye")) {
            System.out.println("    Bye. Hope to see you again soon!");
        } else if (command.equals("giveList")) {
            for (int i = 0; i < index; ++i) {
                int counter = i+1;
                System.out.print("    " + counter + ". ");
                System.out.println(list[i]);
            }

        } else {
            System.out.println("    " + "added: " + command);
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke \n"
                + "     What can I do for you? \n"
                + "    ____________________________________________________________\n";
        System.out.println(greet);

        String line;
        String[] list = new String[100];
        int index = 0;

        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            echoInstruction(line, list, index);
            if (line.equals("bye")) {
                break;
            }
            list[index] = line;
            ++index;
        }


    }
}
