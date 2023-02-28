package duke.exception;

import duke.ui.UI;

import java.util.ArrayList;
import java.util.Arrays;

public class DukeException extends Exception {
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

    private static void catchError(String input) throws UnknownInputFieldError, EmptyTaskDescription {
        String[] inputWords = input.split(" ");
        String command = inputWords[0];
        String[] commandArray = {"todo", "deadline", "event", "list", "mark", "unmark", "delete", "bye"};
        ArrayList<String> commandList = new ArrayList<>(Arrays.asList(commandArray));
        if (!commandList.contains(command)) {
            UI.printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            throw new UnknownInputFieldError();
        } else if (command.equals("list") | command.equals("bye")) {
        } else if (inputWords.length == 1) {
            UI.printMessage("☹ OOPS!!! The description of a " + command + " cannot be empty");
            throw new EmptyTaskDescription();
        }
    }
}