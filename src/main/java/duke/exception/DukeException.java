package duke.exception;

import duke.UI;

import java.util.Arrays;
import java.util.List;

public class DukeException extends Exception  {
    public static boolean hasError(String input){
        try{
            catchError(input);
        } catch (UnknownInputFieldError e) {
            return true;
        } catch (EmptyTaskDescription e){
            return true;
        }
        return false;
    }

    private static void catchError(String input) throws UnknownInputFieldError, EmptyTaskDescription {
        String[] inputWords = input.split(" ");
        String[] taskTypes = {"todo", "deadline", "event", "list", "mark", "unmark", "bye"};
        List taskType = Arrays.asList(taskTypes);
        if (!taskType.contains(inputWords[0])) {
            UI.printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            throw new UnknownInputFieldError();
        } else if (inputWords[0].equals("list") | inputWords[0].equals("bye")) {
        } else if(inputWords.length == 1){
            UI.printMessage("☹ OOPS!!! The description of a " + inputWords[0] + " cannot be empty");
            throw new EmptyTaskDescription();
        }
    }

}