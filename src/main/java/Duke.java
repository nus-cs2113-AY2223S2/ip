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

        runBot();
    }

    public static void runBot() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        int tasksI = 1;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i < tasksI; i++) {
                    Task currTask = TASKS[i];
                    System.out.println(i + ".[" + currTask.getStatusIcon() + "] " + currTask.getDescription());
                }
            } else if (command.contains("mark")) {
                String[] splitCommand = command.split(" ");
                int taskNum = Integer.parseInt(splitCommand[1]);
                Task currTask = TASKS[taskNum];
                if (command.substring(0, 4).equals("mark")) {
                    currTask.mark();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("[" + currTask.getStatusIcon() + "] "  + currTask.getDescription());
                } else {
                    currTask.unmark();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println("[" + currTask.getStatusIcon() + "] "  + currTask.getDescription());
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



