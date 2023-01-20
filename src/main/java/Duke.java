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
        String userInput = "";
        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                System.out.println("    --------------------------------------------------");
                for (int i = 0; i < 100; i++) {
                    if (tasks[i] != null) {
                        System.out.println("    " + (i + 1) + ". " + tasks[i]);
                    } else {
                        break;
                    }
                }
                System.out.println("    --------------------------------------------------");
            } else {
                System.out.println("    --------------------------------------------------");
                System.out.println("     added: " + userInput);
                System.out.println("    --------------------------------------------------\n");
                for (int i = 0; i < 100; i++) {
                    if (tasks[i] == null) {
                        tasks[i] = userInput;
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
