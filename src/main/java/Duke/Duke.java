package Duke;

import java.util.ArrayList;
import java.util.Scanner;

import Commands.PrintCommands;
import Commands.TaskCommand;
import Commands.FileCommands;

import java.io.IOException;

import Exceptions.InvalidTaskDescription;
import Exceptions.InvalidTaskNumberException;
import Exceptions.MissingDescriptionException;
import Exceptions.NullCommandException;
import Tasks.Task;

public class Duke {
    public static void main(String[] args) throws NullCommandException, InvalidTaskDescription, IOException {

        PrintCommands.printWelcomeMessage();

        boolean isExit = false;

        ArrayList<Task> taskList = new ArrayList<Task>(); // Array of lists

        Scanner scan = new Scanner(System.in);

        FileCommands.readFileData(taskList); //read past task data

        while (!isExit) {
            try {
                String input = scan.nextLine();
                String[] command = input.split(" ", 2); // split the input into an array of strings

                switch (command[0]) {

                    case "bye":
                        // terminate clom, print goodbye message, save tasks
                        isExit = true;
                        FileCommands.saveFile(taskList);
                        PrintCommands.printExitMessage();
                        System.exit(0);

                    case "list":
                        // Display list of tasks
                        PrintCommands.printList(taskList);
                        break;

                    case "mark":
                        TaskCommand.markTask(taskList, command, TaskCommand.getTaskIndex(command, taskList));
                        break;

                    case "unmark":
                        TaskCommand.unmarkTask(taskList, command, TaskCommand.getTaskIndex(command, taskList));
                        break;

                    case "todo":
                        TaskCommand.todoTask(taskList, command);
                        break;

                    case "deadline":
                        TaskCommand.deadlineTask(taskList, command);
                        break;

                    case "event":
                        TaskCommand.eventTask(taskList, command);
                        break;

                    case "help":
                        PrintCommands.printHelp();
                        break;

                    case "delete":
                        TaskCommand.deleteTask(taskList, command, TaskCommand.getTaskIndex(command, taskList));
                        break;

                    case "find" :
                        TaskCommand.findTask(taskList, command);
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
            } catch (MissingDescriptionException mde) {
                System.err.println("Task description is missing. Try again.");
            }
        }
        scan.close();
    }
}
