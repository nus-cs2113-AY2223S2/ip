import java.util.Scanner;
// Now it is level 3


public class Duke {
    public static Task[] taskList = new Task [100]; // The size of this list is initialize to be 100
    public static int listTailIndex = 0;

    public static void add(String line) {
        System.out.println("____________________________________________________________");
        System.out.println("added: " + line);
        Task currentTask = new Task(line);
        taskList[listTailIndex] = currentTask;
        listTailIndex++;
        System.out.println("____________________________________________________________");
    }

    public static void list() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listTailIndex; i++) {
            System.out.print(i + 1);
            System.out.println(".[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
        }
        System.out.println("____________________________________________________________" + '\n');
    }

    public static void initialGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void bye() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Greet the user
        initialGreeting();

        // Echos
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                list();
            } else if (line.startsWith("mark")) {
                int taskNumber = Integer.parseInt(line.substring(5));
                taskList[taskNumber - 1].isDone = true;
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [X] "+ taskList[taskNumber - 1].description);
                System.out.println("____________________________________________________________");

            } else if (line.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(line.substring(7));
                taskList[taskNumber - 1].isDone = false;
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [ ] "+ taskList[taskNumber - 1].description);
                System.out.println("____________________________________________________________");

            } else {
                add(line);
            }
            line = in.nextLine();
        }

        // Bye and terminate the program
        bye();

    }
}
