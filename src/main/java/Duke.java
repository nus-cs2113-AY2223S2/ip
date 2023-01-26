import java.util.Scanner;

public class Duke {

    static final Task[] TASKS = new Task[101];

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        run();
    }

    public static void run() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        int tasksI = 1;
        while (!command.equals("bye")) {
            Task task = new Task(command);
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i < tasksI; i++) {
                    System.out.println(i + ".[" + TASKS[i].getStatusIcon() + "] " + TASKS[i].getDescription());
                }
            } else if (command.contains("mark")) {
                if (command.substring(0, 4).equals("mark")) {
                    int taskNum = Integer.parseInt("" + command.charAt(5));
                    TASKS[taskNum].mark();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("[" + TASKS[taskNum].getStatusIcon() + "] "  + TASKS[taskNum].getDescription());
                } else {
                    int taskNum = Integer.parseInt("" + command.charAt(7));
                    TASKS[taskNum].unmark();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println("[" + TASKS[taskNum].getStatusIcon() + "] "  + TASKS[taskNum].getDescription());
                }
            } else {
                TASKS[tasksI] = new Task(command);
                tasksI++;
                System.out.println("added: " + command);
            }
            command = scan.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}



