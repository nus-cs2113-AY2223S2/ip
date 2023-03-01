package duke.parser;

import duke.command.AddCommand;
import duke.command.ChangeStatusCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.exceptions.EmptyTaskException;
import duke.exceptions.InvalidCommandException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.Scanner;
/**
 * Represents the parser used to parse and handle user's inputs
 * according to the command type
 */
public class Parser {

    public static Boolean isRunning = true;
    public static Boolean isRunning(){
        return isRunning;
    }
    public static String entry;
    public static Scanner input = new Scanner(System.in);
    public static void readInput() {
        entry = input.nextLine().trim();
    }

    public static String getCommand() {
        String[] userInput = entry.split(" ",2);
        String command = userInput[0].trim();
        return command;
    }

    public static String getArguments(){
        String[] userInput = entry.split(" ",2);
        if (userInput.length > 1) {
            String arguments = userInput[1].trim();
            return arguments;
        }
        else{
            return null;
        }
    }

    /**
     * Executes command from the user
     *
     * @param tasks Task list containing all saved task
     */
    public static void handleCommand(TaskList tasks){
        try {
            readInput();
            String command = getCommand();
            String arguments = getArguments();
            switch (command) {
            case "list":
                TaskList.printList(tasks);
                break;

            case "mark":
                ChangeStatusCommand.markTask(tasks, arguments);
                break;

            case "unmark":
                ChangeStatusCommand.unmarkTask(tasks, arguments);
                break;

            case "deadline":
                AddCommand.addDeadline(tasks, arguments);
                break;

            case "todo":
                try {
                    AddCommand.addTodo(tasks, arguments);
                } catch (EmptyTaskException e) {
                    e.printErrorMessage();
                    Ui.printBorder();
                }
                break;

            case "event":
                AddCommand.addEvent(tasks, arguments);
                break;

            case "delete":
                DeleteCommand.deleteTask(tasks, arguments);
                break;

            case "find":
                FindCommand.findTask(tasks, arguments);
                break;

            case "bye":
                isRunning = false;
                break;

            default:
                throw new InvalidCommandException();
            }
        } catch(InvalidCommandException e){
        e.printErrorMessage();
        Ui.printBorder();
        }
    }
}
