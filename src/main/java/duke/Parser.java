package duke;

import duke.exceptions.MissingDescriptionException;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;

public class Parser {

    /**
     * Splits the user input based on the splitter string
     * into n items.
     * @param line line to split
     * @param splitter String to split with.
     * @param n number of items to split into.
     * @return
     */
    public static String[] splitCommand(String line, String splitter, int n){
        return line.split(splitter, n);
    }

    /**
     * Creates an event item based on descriptions given by the user.
     * @param words the task description.
     * @return the event item to be created.
     * @throws MissingDescriptionException description is empty.
     */
    public static Event parseEvent (String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] description = splitCommand(words[1], "/from", 2);
        if (description.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] dates = splitCommand(description[1], "/to", 2);
        if (dates.length != 2) {
            throw new MissingDescriptionException();
        }
        Event event = new Event(description[0], "E", dates[0], dates[1]);
        return event;
    }

    /**
     * Creates a deadline item based on descriptions given by the user.
     * @param words the task description.
     * @return the deadline item to be created.
     * @throws MissingDescriptionException description is empty.
     */
    public static Deadline parseDeadline (String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] description = splitCommand(words[1], "/by", 2);
        if (description.length != 2) {
            throw new MissingDescriptionException();
        }
        Deadline deadline = new Deadline(description[0], "D", description[1]);
        return deadline;
    }

    /**
     * Creates a task item based on descriptions given by the user.
     * @param words the task description.
     * @return the task item to be created.
     * @throws MissingDescriptionException description is empty.
     */
    public static Task parseTodo (String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String description = words[1];
        Task todo = new Task(description, "T");
        return todo;
    }
}
