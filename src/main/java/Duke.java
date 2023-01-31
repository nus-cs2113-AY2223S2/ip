import java.util.Scanner;
public class Duke {

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();

        Task[] list = new Task[100]; //store tasks in an array
        int count = 0; //keep track of number of tasks in list

        while (!(command.equals("bye"))) {
            //list tasks
            if (command.equals("list")) {
                printLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= count; i++) {
                    System.out.println(i + "." + list[i - 1]);
                }
                printLine();
            }
            else {
                String[] input = command.split(" ", 2); //only check the first word for "mark" or "unmark"

                //mark task
                if (input[0].equals("mark")) {
                    int index = Integer.parseInt(input[1]);
                    Task t = list[index-1];
                    t.markAsDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                    printLine();
                }
                //un-mark task
                else if (input[0].equals("unmark")) {
                    int index = Integer.parseInt(input[1]);
                    Task t = list[index-1];
                    t.markNotDone();
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
                    printLine();
                }
                //todo
                else if (input[0].equals("todo")) {
                    Task t = new Todo(input[1]);
                    list[count] = t;
                    count++;
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + count + " tasks in the list.");
                    printLine();

                }
                //deadline
                else if (input[0].equals("deadline")) {
                    String[] doBy = input[1].split("by ", 2);

                    Task t = new Deadline(doBy[0], doBy[1]);
                    list[count] = t;
                    count++;
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + count + " tasks in the list.");
                    printLine();
                }
                //event
                else if (input[0].equals("event")) {
                    String[] start = input[1].split("from ", 2);
                    String[] end = start[1].split("to ", 2);

                    Task t = new Event(start[0], end[0], end[1] );
                    list[count] = t;
                    count++;
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + count + " tasks in the list.");
                    printLine();
                }
                //unlabelled
                else {
                    Task t = new Task(command);
                    list[count] = t;
                    count++;
                    printLine();
                    System.out.println("added: " + t.description); //new item added
                    printLine();
                }
            }
            command = in.nextLine(); //read next command
        }

        //exit loop when command received is "bye"
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printLine();
    }
}

