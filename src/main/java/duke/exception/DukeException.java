package duke.exception;

import duke.tasklist.TaskList;
import duke.ui.UI;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Exception handler class that catches error
 */
public class DukeException extends Exception {

    /**
     * Tries to catch error in the user input
     *
     * @param input input that might contain error
     * @return boolean of whether there is an error
     */
    public static boolean hasError(String input) {
        try {
            catchError(input);
        } catch (UnknownInputFieldError err) {
            return true;
        } catch (EmptyTaskDescription err) {
            return true;
        }
        return false;
    }

    /**
     * Executes the command according to command type
     *
     * @param input input that might contain error
     * @throws UnknownInputFieldError if error occurred due to unknown user input
     * @throws EmptyTaskDescription if error occurred due to lack of task detail
     */
    private static void catchError(String input) throws UnknownInputFieldError, EmptyTaskDescription {
        String[] inputWords = input.split(" ");
        String command = inputWords[0];
        String[] commandArray = {"todo", "deadline", "event", "list", "mark", "unmark", "delete", "bye", "find"};
        ArrayList<String> commandList = new ArrayList<>(Arrays.asList(commandArray));
        if (!commandList.contains(command)) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            throw new UnknownInputFieldError();
        } else if (command.equals("list") | command.equals("bye")) {
        } else if (inputWords.length == 1) {
            System.out.println("☹ OOPS!!! The description of a " + command + " cannot be empty");
            throw new EmptyTaskDescription();
        }
    }
    /**
     * Try to catch invalid index provided by user
     *
     * @param index input of the task that might be wrongly given
     * @throws InvalidIndexError if error occurred due to invalid index provided by user
     * @throws EmptyListError if error occurred due to empty list in the tasklist
     */
    public static void catchInvalidIndexError(int index, TaskList tasks) throws InvalidIndexError, EmptyListError {
        if (index < 0 | index > tasks.sizeOfList()){
            throw new InvalidIndexError();
        }
    }
}