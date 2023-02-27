import java.util.Scanner;
import Exceptions.InvalidTaskDescription;
import Exceptions.InvalidTaskNumberException;
import Exceptions.MissingDescriptionException;
import Exceptions.NullCommandException;
import Tasks.Task;

public class Duke {
    public static void main(String[] args) throws NullCommandException, InvalidTaskDescription {

        PrintCommands.printWelcomeMessage();

        int counter = 0; // number of items in the list
        boolean isExit = false;

        Task t[] = new Task[100]; // task list

        while (!isExit) {
            try (Scanner scan = new Scanner(System.in)) {
                String input = scan.nextLine();
                String[] command = input.split(" ", 2); // split the input into an array of strings

                try {
                    switch (command[0]) {

                        case "bye":
                            // terminate clom, print goodbye message
                            isExit = true;
                            PrintCommands.printExitMessage();
                            System.exit(0);

                        case "list":
                            // Display list of tasks
                            PrintCommands.printList(t, counter);
                            break;

                        case "mark":
                            TaskCommand.markTask(counter, t, command, TaskCommand.getTaskIndex(command, counter));
                            break;

                        case "unmark":
                            TaskCommand.unmarkTask(counter, t, command, TaskCommand.getTaskIndex(command, counter));
                            break;

                        case "todo":
                            counter = TaskCommand.todoTask(counter, t, command);
                            break;

                        case "deadline":
                            counter = TaskCommand.deadlineTask(counter, t, command);
                            break;

                        case "event":
                            counter = TaskCommand.eventTask(counter, t, command);
                            break;

                        case "help":
                            PrintCommands.printHelp();
                            break;

                        default:
                            throw new NullCommandException(
                                    "Unrecognised command. Type 'help' for a list of recognised commands");
                    }
                } catch (NumberFormatException nfe) {
                    // Mark/Unmark is not followed by an Integer
                    System.out.println("mark/unmark should be followed by an integer! Please try again.");
                } catch (InvalidTaskNumberException itne) {
                    // mark/unmark is followed by an integer which is either too small or too large
                    System.out.println("The number you entered is out of range! Please try again");
                    // } catch (InvalidTaskDescription itd) {
                    // // Command is valid but does not follow the proper format
                    // System.out.println("Please follow the proper format for adding tasks. Type
                    // HELP for help");
                } catch (MissingDescriptionException mde) {
                    System.err.println("Task description is missing. Try again.");
                }
            }
        }
    }
}
