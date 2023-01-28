import java.util.Scanner;

public class Duke {
    private static final TaskList tasks = new TaskList();

    // handle commands by the user.
    private static void handleInput(String input) {
        // If string contains mark, could be mark or unmark
        // if not, it will fallthrough to the next conditional block
        if (input.contains("mark")) {
            String[] command = input.split(" ");
            int number = Integer.parseInt(command[1]);
            // Check if user inputs task number not out of range
            if (number > TaskList.getNumberOfTasks()) {
                System.out.println("This task doesn't exist.");
                return;
            }

            // Check if the command is "mark" or "unmark"
            if (command[0].equals("mark")) {
                tasks.markAsDone(number);
                return;
            }
            else if (command[0].equals("unmark")) {
                tasks.markAsUndone(number);
                return;
            }
        }

        // Check user input against commands.
        if (input.equals("bye")) {
            return;
        } else if (input.equals("list")) {
            tasks.printContents();
        } else if (input.equals("help")) {
            System.out.println(Message.HELP_MESSAGE);
        } else {
            tasks.addTask(input);
            System.out.println("added: " + input);
        }
    }

    public static void main(String[] args) {

        Message.greet();

        // Input variables initialised.
        Scanner myScanner = new Scanner(System.in);
        String userInput = "";

        // The main loop, which ends when user says Bye
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.print("> ");
            userInput = myScanner.nextLine();
            handleInput(userInput.toLowerCase());
            Message.printHLine();
        }

        // Exit message
        Message.exit();
    }
}
