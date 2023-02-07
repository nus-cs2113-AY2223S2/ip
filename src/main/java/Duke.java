import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*
         * String logo = " ____        _        \n"
         * + "|  _ \\ _   _| | _____ \n"
         * + "| | | | | | | |/ / _ \\\n"
         * + "| |_| | |_| |   <  __/\n"
         * + "|____/ \\__,_|_|\\_\\___|\n";
         */
        String logo = " ____   _              __    __ \n"
                + "/  __\\ | |     _____  |   \\/   |\n"
                + "| /    | |    /  _  \\ | |\\  /| |\n"
                + "| \\ __ | |___ | |_| | | | \\/ | |\n"
                + "\\____/ |____/ \\_____/ |_|    |_|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm CLoM!\n");
        System.out.println(" What can I do for you?\n");

        int counter = 0; // number of items in the list
        boolean isExit = false;

        Task t[] = new Task[100]; // task list

        while (!isExit) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            if (input.equals("bye")) {
                // terminate clom, print goodbye message
                isExit = true;
                System.out.println("______________\n");
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println("______________\n");

            } else if (input.equals("list")) {
                // Display list of tasks
                System.out.println("______________\n");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ".[" + t[i].getStatusIcon() + "]" + t[i].description);
                }
                System.out.println("______________\n");
                // isExit = false;

            } else if (input.contains(" ")) {

                String[] command = input.split(" "); // split the input into an array of strings

                if (command[0].equals("mark")) {
                    // mark task as done
                    System.out.println("Nice! I've marked this task as done:");
                    int markIndex = Integer.parseInt(command[1]) - 1;
                    t[markIndex].markAsDone();
                    System.out.println("[" + t[markIndex].getStatusIcon() + "]" + t[markIndex].description);

                } else if (command[0].equals("unmark")) {
                    // mark task as undone
                    System.out.println("OK, I've marked this task as not done yet:");
                    int unmarkIndex = Integer.parseInt(command[1]) - 1;
                    t[unmarkIndex].markAsNotDone();
                    System.out.println("[" + t[unmarkIndex].getStatusIcon() + "]" + t[unmarkIndex].description);

                }
            } else {
                // Add task to the task list
                t[counter] = new Task(input);
                System.out.println("added: " + t[counter].description);
                counter++;

            }
        }
    }
}
