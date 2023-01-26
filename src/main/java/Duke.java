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
        command = in.nextLine();

        Task[] list = new Task[100]; //store tasks in an array
        int count = 0; //keep track of number of tasks in list

        while (!(command.equals("bye"))) {
            //list tasks
            if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= count; i++) {
                    System.out.println(i + "." + "[" + list[i-1].getStatusIcon() + "] " + list[i - 1].description);
                }
                System.out.println("____________________________________________________________");
            }
            else {
                String arr[] = command.split(" ", 2); //only check the first word for "mark" or "unmark"

                //mark task
                if (arr[0].equals("mark")) {
                    int index = Integer.parseInt(arr[1]);
                    Task t = list[index-1];
                    t.markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + t.getStatusIcon() + "] " + t.description);
                    System.out.println("____________________________________________________________");
                }
                //un-mark task
                else if (arr[0].equals("unmark")) {
                    int index = Integer.parseInt(arr[1]);
                    Task t = list[index-1];
                    t.unmarkTask();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + t.getStatusIcon() + "] " + t.description);
                    System.out.println("____________________________________________________________");
                }
                //add new task
                else {
                    Task t = new Task(command);
                    list[count] = t;
                    count++;
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + t.description); //new item added
                    System.out.println("____________________________________________________________");
                }
            }
            command = in.nextLine(); //read next command
        }

        //exit loop when command received is "bye"
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon! :)");
        System.out.println("____________________________________________________________");
    }
}

