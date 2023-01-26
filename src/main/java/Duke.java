import java.util.Scanner;

public class Duke {

    static final String[] TASKS = new String[101];

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
            if (command.equals("list")) {
                for (int i = 1; i < tasksI; i++) {
                    System.out.println(i + ". " + TASKS[i]);
                }
            } else {
                TASKS[tasksI] = command;
                tasksI++;
                System.out.println("added: " + command);
            }
            command = scan.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}



