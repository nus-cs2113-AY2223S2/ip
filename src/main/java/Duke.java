import java.util.Scanner;

public class Duke {
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

        // Echoes user input until "bye" is entered
        String[] tasks = new String[100];
        boolean[] done = new boolean[100];
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
                    char doneChar = done[i] ? 'X' : ' ';
                    if (tasks[i] != null) {
                        System.out.println("    " + (i + 1) + ".[" + doneChar + "] " + tasks[i]);
                    } else {
                        break;
                    }
                }
                System.out.println("    --------------------------------------------------");
            } else if (userInput.startsWith("mark")) {
                int taskNo = Integer.parseInt(userInput.substring(5));
                System.out.println("    --------------------------------------------------");
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      [X] " + tasks[taskNo - 1]);
                System.out.println("    --------------------------------------------------");
                done[taskNo - 1] = true;
            } else if (userInput.startsWith("unmark")) {
                int taskNo = Integer.parseInt(userInput.substring(7));
                System.out.println("    --------------------------------------------------");
                System.out.println("    I've marked this task as not done yet:");
                System.out.println("      [ ] " + tasks[taskNo - 1]);
                System.out.println("    --------------------------------------------------");
                done[taskNo - 1] = false;
            } else {
                System.out.println("    --------------------------------------------------");
                System.out.println("     added: " + userInput);
                System.out.println("    --------------------------------------------------\n");
                for (int i = 0; i < 100; i++) {
                    if (tasks[i] == null) {
                        tasks[i] = userInput;
                        done[i] = false;
                        break;
                    }
                }
            }
        }
        System.out.println("    -----------------------------------");
        System.out.println("    Bye! See you later!");
        System.out.println("    -----------------------------------");
    }
}
