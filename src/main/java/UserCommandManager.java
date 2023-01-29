import java.util.Scanner;

public class UserCommandManager {
    TaskManager myList = new TaskManager();

    /**
     * checks if user input is valid and adds the event to list
     *
     * @param userInputArray array of string containing user deadline details
     */

    public void handleDeadlineCommand(String[] userInputArray) {
        if (userInputArray.length < 2) {
            System.out.println("Invalid input, make sure you use /by <date>");
            return;
        }
        String description = userInputArray[0];
        String dueBy = userInputArray[1];
        myList.addToList(description, dueBy);
    }

    /**
     * checks if user input is valid and adds the event to list
     *
     * @param userInput String containing user event details
     */

    public void handleEventCommand(String userInput) {
        final String[] userInputArray = userInput.trim().split("/from|/to");
        if (userInputArray.length < 3) {
            System.out.println("Invalid input, make sure you use /from <start> and /to <end>");
            return;
        }
        String description = userInputArray[0];
        String startTime = userInputArray[1];
        String endTime = userInputArray[2];
        myList.addToList(description, startTime, endTime);
    }

    /**
     * prints instructions to use the program if
     * an unknown command is entered
     */

    public static void handleUnknownCommand() {
        System.out.println("I don't understand...\n" +
                "Try these commands: <event> /from <start> /to <end>\n" +
                "<deadline> /by <end> \n" +
                "<todo>\n" +
                "<list>");
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
        switch (userCommand) {
        case "list":
            myList.printList();
            break;
        case "mark":
            myList.markAsDone(scanner.nextInt() - 1);
            break;
        case "unmark":
            myList.markAsUndone(scanner.nextInt() - 1);
            break;
        case "todo":
            myList.addToList(scanner.nextLine());
            break;
        case "deadline":
            handleDeadlineCommand(scanner.nextLine().trim().split("/by"));
            break;
        case "event":
            handleEventCommand(userInput);
            break;
        default:
            handleUnknownCommand();
            break;
        }
    }
}
