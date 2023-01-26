import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String command;
        Scanner in = new Scanner(System.in);
        String[] list = new String[100]; //store items in an array
        int count = 0; //keep track of number of items in list

        command = in.nextLine();
        while (!(command.equals("bye"))) {
            if (command.equals("list"))
            {
                System.out.println("____________________________________________________________");
                for (int i = 1; i <= count; i++ )
                {
                    System.out.println(i + ". " + list[i-1]);
                }
                System.out.println("____________________________________________________________");
                command = in.nextLine(); //read next command

            } else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + command); //new item added
                System.out.println("____________________________________________________________");
                list[count] = command;
                count++;
                command = in.nextLine(); //read next command
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

