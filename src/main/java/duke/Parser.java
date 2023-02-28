package duke;

import duke.exception.EmptyListError;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {
    public static String getFirstCommand(String input) {
        String[] inputWords = input.split(" ");
        return inputWords[0];
    }

}
