import java.util.Scanner;

public class UserCommandManager {

    TaskManager myList = new TaskManager();

    /**
     * Prints out the exit message to the user
     */
    public static void exitProgram() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
        System.exit(0);
    }

    /**
     * Handles the command (i.e. type of task) by user
     * and parse the arguments to add to task list
     *
     * @param userCommand type of action user wants to perform
     * @param userInput   arguments to the action (i.e. task description)
     */

    public void handleCommands(String userCommand, String userInput) {
        Scanner scanner = new Scanner(userInput);

        if (userCommand.equals("bye")) {
            exitProgram();
        }
        int taskIndex;
        String description;
        switch (userCommand) {
        case "list":
            myList.printList();
            break;
        case "mark":
            taskIndex = scanner.nextInt() - 1;
            myList.markAsDone(taskIndex);
            break;
        case "unmark":
            taskIndex = scanner.nextInt() - 1;
            myList.markAsUndone(taskIndex);
            break;
        case "todo":
            userInput = scanner.nextLine();
            myList.addToList(userInput);
            break;
        case "deadline":
            userInput = scanner.nextLine();
            final String[] splitArgs = userInput.trim().split("/by");
            if (splitArgs.length < 2) {
                System.out.println("Invalid input, make sure you use /by <date>");
                break;
            }
            description = splitArgs[0];
            String dueBy = splitArgs[1];
            myList.addToList(description, dueBy);
            break;
        case "event":
            final String[] splitDuration = userInput.trim().split("/from|/to");
            if (splitDuration.length < 3) {
                System.out.println("Invalid input, make sure you use /from <start> and /to <end>");
                break;
            }
            description = splitDuration[0];
            String startDate = splitDuration[1];
            String endDate = splitDuration[2];
            myList.addToList(description, startDate, endDate);
            break;
        default:
            System.out.println("I don't understand...\n" +
                    "Try these commands: <event> /from <start> /to <end>\n" +
                    "<deadline> /by <end> \n" +
                    "<todo>\n" +
                    "<list>");
        }
    }
}
