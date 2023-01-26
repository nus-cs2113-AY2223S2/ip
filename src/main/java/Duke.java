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
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= count; i++ )
                {
                    System.out.println(i + "." + list[i-1]);
                }
                System.out.println("____________________________________________________________");
            } else {
                String arr[] = command.split(" ", 2);
                //mark task as done
                if (arr[0].equals("mark")) {
                    int index = Integer.parseInt(arr[1]);
                    String cmd[] = list[index-1].split("] ");
                    list[index-1] = "[X] " + cmd[1]; //cmd[1] is the original command
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list[index-1]);
                    System.out.println("____________________________________________________________");
                }
                //unmark task
                else if (arr[0].equals("unmark")) {
                    int index = Integer.parseInt(arr[1]);
                    String cmd[] = list[index - 1].split("] ");
                    list[index - 1] = "[ ] " + cmd[1]; //cmd[1] is the original command
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list[index - 1]);
                    System.out.println("____________________________________________________________");
                }
                //add new task
                else {
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + command); //new item added
                    System.out.println("____________________________________________________________");
                    list[count] = "[ ] " + command;
                    count++;
                }
            }
            command = in.nextLine(); //read next command
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon! :)");
        System.out.println("____________________________________________________________");
    }
}

