import java.util.Scanner;

public class Orca {
    public static void main(String[] args) {
        String logo = "     _______  ______    _______  _______ \n"
                + "    |       ||    _ |  |       ||   _   | \n"
                + "    |   _   ||   | ||  |       ||  |_|  | \n"
                + "    |  | |  ||   |_||_ |       ||       | \n"
                + "    |  |_|  ||    __  ||      _||       | \n"
                + "    |       ||   |  | ||     |_ |   _   | \n"
                + "    |_______||___|  |_||_______||__| |__| \n";

        System.out.println("    --------------------------------------------------");
        System.out.println(logo);
        System.out.println("    Hello! I'm Orca, your assistant chatbot.");
        System.out.println("    What can I do for you?");
        System.out.println("    --------------------------------------------------\n");

        Task[] tasks = new Task[100];
        int taskCount = 0;
        String userInput = "";
        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                System.out.println("    --------------------------------------------------");
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < 100; i++) {
                    if (tasks[i] != null) {
                        System.out.println("    " + (i + 1) + "." + tasks[i].toString());
                    } else {
                        break;
                    }
                }
                System.out.println("    --------------------------------------------------\n");
            } else if (userInput.startsWith("mark")) {
                int taskNo = Integer.parseInt(userInput.substring(5));
                tasks[taskNo - 1].setDone(true);
                System.out.println("    --------------------------------------------------");
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + tasks[taskNo - 1].toString());
                System.out.println("    --------------------------------------------------\n");
            } else if (userInput.startsWith("unmark")) {
                int taskNo = Integer.parseInt(userInput.substring(7));
                tasks[taskNo - 1].setDone(false);
                System.out.println("    --------------------------------------------------");
                System.out.println("    I've marked this task as not done yet:");
                System.out.println("      " + tasks[taskNo - 1].toString());
                System.out.println("    --------------------------------------------------\n");
            } else if (userInput.startsWith("todo")) {
                tasks[taskCount] = new Todo(userInput.substring(5));
                taskCount++;
                System.out.println(" --------------------------------------------------");
                System.out.println("      Got it. I've added this task:");
                System.out.println("          " + tasks[taskCount - 1]);
                System.out.println("        Now you have " + taskCount + " tasks in the list.");
                System.out.println(" --------------------------------------------------\n");
            } else if (userInput.startsWith("deadline")) {
                String[] deadline = userInput.substring(9).split(" /by ");
                tasks[taskCount] = new Deadline(deadline[0], deadline[1]);
                taskCount++;
                System.out.println(" --------------------------------------------------");
                System.out.println("      Got it. I've added this task:");
                System.out.println("          " + tasks[taskCount - 1]);
                System.out.println("        Now you have " + taskCount + " tasks in the list.");
                System.out.println(" --------------------------------------------------\n");
            } else if (userInput.startsWith("event")) {
                int fromIndex = userInput.indexOf(" /from ");
                int toIndex = userInput.indexOf(" /to ");
                String description = userInput.substring(6, fromIndex);
                String from = userInput.substring(fromIndex + 7, toIndex);
                String to = userInput.substring(toIndex + 5);
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println(" --------------------------------------------------");
                System.out.println("      Got it. I've added this task:");
                System.out.println("          " + tasks[taskCount - 1]);
                System.out.println("        Now you have " + taskCount + " tasks in the list.");
                System.out.println(" --------------------------------------------------\n");
            }
        }
        System.out.println("    -----------------------------------");
        System.out.println("    Bye! See you later!");
        System.out.println("    -----------------------------------");
    }
}
