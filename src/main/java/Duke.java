import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\nHola! I'm Duke\n" + logo);
        System.out.println("How can I help you today?\n____________________________________________________________");

        // store in array
        Task[] todos = new Task[100];
        // num of items in list
        int num;
        num = 0;

        // take in input
        String cmd;
        Scanner in = new Scanner(System.in);
        cmd = in.nextLine();

        // if input is bye
        while (!(cmd.equals("bye"))) {
            if (cmd.equals("list")) {
                System.out.println("____________________________________________________________");

                // store in list
                for (int i = 1; num >= i; i += 1) {
                    System.out.println(i + "." + "[" + todos[i-1].getStatusIcon() + "] " + todos[i - 1].description);
                }

                System.out.println("____________________________________________________________");
            } else {
                String list[] = cmd.split(" ",2);

                if (list[0].equals("unmark")) {
                    int pt;
                    pt = Integer.parseInt(list[1]);
                    Task command = todos[pt-1];
                    command.unmarkTask();
                    System.out.println("____________________________________________________________");
                    System.out.println("This task is marked as undone:");
                    System.out.println("[" + command.getStatusIcon() + "] " + command.description);
                    System.out.println("____________________________________________________________");
                } else if (list[0].equals("mark")) {
                    int pt;
                    pt = Integer.parseInt(list[1]);
                    Task command = todos[pt-1];
                    command.markTask();
                    System.out.println("____________________________________________________________");
                    System.out.println("This task is marked as done:");
                    System.out.println("[" + command.getStatusIcon() + "] " + command.description);
                    System.out.println("____________________________________________________________");
                } else {
                    Task command = new Task(cmd);
                    todos[num] = command;
                    num += 1;
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + command.description);
                    System.out.println("____________________________________________________________");
                }
            }
            // read next command
            cmd = in.nextLine();
        }

        System.out.println("____________________________________________________________\nBye! See you soon!");
        System.out.println("____________________________________________________________");
    }
}
