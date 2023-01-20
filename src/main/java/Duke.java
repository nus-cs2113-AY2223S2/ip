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
        String userInput = "";
        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println("    --------------------------------------------------");
            System.out.println("    " + userInput);
            System.out.println("    --------------------------------------------------\n");
        }

        System.out.println("    -----------------------------------");
        System.out.println("    Bye! See you later!");
        System.out.println("    -----------------------------------");
    }
}
