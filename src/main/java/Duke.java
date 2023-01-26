import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String command = "";
        Scanner in = new Scanner(System.in);

        int textNum = 0;                            // number of texts stored
        Task[] storedTasks = new Task[100];         // array of stored texts
        while (!command.equals("bye")) {
            command = in.nextLine();
            String[] commandWords = command.split(" ");
            if (command.equals("list")) {
                System.out.println("To-do: ");
                System.out.println("________________________________________");
                for(int i = 0; i < textNum; i++)
                {
                    System.out.println((i + 1) + ". "+ storedTasks[i].getStatusIcon() + " " + storedTasks[i].getDescription());
                }
                System.out.println("________________________________________");
            } else if (commandWords[0].equals("mark")) {
                int taskIndex = Integer.parseInt(commandWords[1]) - 1;
                storedTasks[taskIndex].setDone(true);
                System.out.println("Nice! I've marked this task as done.");
                System.out.println(storedTasks[taskIndex].getStatusIcon() + " " + storedTasks[taskIndex].getDescription());
            } else if (commandWords[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(commandWords[1]) - 1;
                storedTasks[taskIndex].setDone(false);
                System.out.println("OK, I've marked this task as not done yet.");
                System.out.println(storedTasks[taskIndex].getStatusIcon() + " " + storedTasks[taskIndex].getDescription());
            } else if (!command.equals("bye")) {
                System.out.println("added: " + command);
                storedTasks[textNum] = new Task(command);
                textNum++;
            }

        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
