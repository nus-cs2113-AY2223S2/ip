package duke.parser;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidFormatException;

public class TaskParser {
    public static Task getTaskFromCommand(String[] inputArray) throws InvalidFormatException, InvalidCommandException {
        Task task;
        String command = inputArray[0];

        switch (command) {
        case "deadline":
            if (!inputArray[1].contains("/by")) {
                throw new InvalidFormatException("/by");
            }
            String[] deadlineDetails = inputArray[1].split(" /by ", 2);

            task = new Deadline(deadlineDetails[0],deadlineDetails[1]);
            break;

        case "event":
            if (!inputArray[1].contains("/from") && !inputArray[1].contains("/to")) {
                throw new InvalidFormatException("/from", "/to");
            }
            //splits input according to eventDescription, /from, /to
            String[] eventDetails = inputArray[1].split(" /from | /to ", 3);

            task = new Event(eventDetails[0],eventDetails[1],eventDetails[2]);
            break;

        case "todo":
            String todoDetails = inputArray[1];
            task = new Todo(todoDetails);
            break;

        default:
            throw new InvalidCommandException();

        }
        return task;
    }
}
