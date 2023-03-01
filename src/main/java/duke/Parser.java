package duke;

import duke.exceptions.MissingDescriptionException;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;

public class Parser {
    public static String[] splitCommand(String line, String splitter, int n){
        return line.split(splitter, n);
    }

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

    public static Task parseTodo (String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String description = words[1];
        Task todo = new Task(description, "T");
        return todo;
    }
}
