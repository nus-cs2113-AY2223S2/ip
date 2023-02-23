package duke;

import duke.exceptions.DukeWrongArgsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.util.Arrays;

public class Parser {

    public static Todo parseTodo(String message) throws StringIndexOutOfBoundsException {
        int secondArgStartIndex = 5;
        if (message.length() < secondArgStartIndex) {
            throw new StringIndexOutOfBoundsException();
        } else {
            return new Todo(message.substring(secondArgStartIndex));
        }
    }

    public static Deadline parseDeadline(String[] message) throws DukeWrongArgsException {
        int descriptionStartIndex = 1;
        int descriptionEndIndex = 0;
        int endDateStartIndex = 0;
        int endDateEndIndex = message.length;

        for (int i = 2; i < message.length; i++) {
            if (message[i].equalsIgnoreCase("/by")) {
                descriptionEndIndex = i;
                endDateStartIndex = i + 1;
                break;
            }
        }

        // Checks if correct argument is provided
        if (endDateStartIndex == 0) {
            throw new DukeWrongArgsException();
        }

        String[] descriptionArray = Arrays.copyOfRange(message, descriptionStartIndex, descriptionEndIndex);
        String[] endDateArray = Arrays.copyOfRange(message, endDateStartIndex, endDateEndIndex);

        String[] deadlineArray = new String[2];
        deadlineArray[0] = String.join(" ", descriptionArray);
        deadlineArray[1] = String.join(" ", endDateArray);

        return new Deadline(deadlineArray[0], deadlineArray[1]);
    }

    public static Event parseEvent(String[] message) throws DukeWrongArgsException {
        int descriptionStartIndex = 1;
        int descriptionEndIndex = 0;
        int startDateStartIndex = 0;
        int startDateEndIndex = 0;
        int endDateStartIndex = 0;
        int endDateEndIndex = message.length;

        for (int i = 1; i < message.length; i++) {
            if (message[i].equalsIgnoreCase("/from")) {
                descriptionEndIndex = i;
                startDateStartIndex = i + 1;
            }
            if (message[i].equalsIgnoreCase("/to")) {
                startDateEndIndex = i;
                endDateStartIndex = i + 1;
                break;
            }
        }

        if (startDateStartIndex == 0 || startDateEndIndex == 0) {
            throw new DukeWrongArgsException();
        }

        String[] descriptionArray = Arrays.copyOfRange(message, descriptionStartIndex, descriptionEndIndex);
        String[] startDateArray = Arrays.copyOfRange(message, startDateStartIndex, startDateEndIndex);
        String[] endDateArray = Arrays.copyOfRange(message, endDateStartIndex, endDateEndIndex);

        String[] eventArray = new String[3];
        eventArray[0] = String.join(" ", descriptionArray);
        eventArray[1] = String.join(" ", startDateArray);
        eventArray[2] = String.join(" ", endDateArray);

        return new Event(eventArray[0], eventArray[1], eventArray[2]);
    }
}
