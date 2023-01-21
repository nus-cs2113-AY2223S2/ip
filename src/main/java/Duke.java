import java.util.Scanner;

public class Duke {
    private static final TaskList tasks = new TaskList();

    public static void main(String[] args) {

        Message.greet();

        // Input variables initialised.
        Scanner myScanner = new Scanner(System.in);
        String userInput = "";

        // The main loop, which ends when user says Bye
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.print("Input: ");
            userInput = myScanner.nextLine();

            // Check user input against commands.
            switch(userInput.toLowerCase()) {
            case "bye":
                break;
            case "list":
                tasks.printContents();
                break;
            default:
                tasks.addTask(userInput);
                System.out.println("added: " + userInput);
            }

            Message.printHLine();
        }

        // Exit message
        Message.exit();
    }
}
