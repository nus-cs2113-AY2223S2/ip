import java.util.Scanner;

public class Duke {
    private static final TaskList tasks = new TaskList();

    /**
     * addTask based on command
     * @param command ["todo", "deadline", or "event"]
     * @param commandArgs full string of commands, not yet split
     */
    private static void addNewTask(String command, String commandArgs) {
        switch(command) {
        case "todo":
            //args should just be the Description
            tasks.addTask(new Todo(commandArgs, TaskList.getNumberOfTasks()));
            break;
        case "deadline":
            String[] deadlineArgs = commandArgs.split("/by");
            tasks.addTask(new Deadline(deadlineArgs[0], TaskList.getNumberOfTasks(), deadlineArgs[1]));
            break;
        case "event":
            String[] eventArgs = commandArgs.split("/from")[1].split("/to");
            tasks.addTask(new Event(eventArgs[0], TaskList.getNumberOfTasks(), eventArgs[1], eventArgs[2]));
            break;
        }
    }
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
            System.out.print(tasks.getTaskListString());
        } else if (input.equals("help")) {
            System.out.print(Command.MESSAGE_HELP);
        } else {
            String[] commands = Command.splitCommandAndArgs(input);
            addNewTask(commands[0], commands[1]);
        }
    }

    public static void main(String[] args) {

        Command.greet();

        // Input variables initialised.
        Scanner myScanner = new Scanner(System.in);
        String userInput = "";

        // The main loop, which ends when user says Bye
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.print("> ");
            userInput = myScanner.nextLine();
            handleInput(userInput.toLowerCase());
            Command.printHLine();
        }

        // Exit message
        Command.exit();
    }
}
