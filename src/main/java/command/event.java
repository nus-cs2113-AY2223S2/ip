package command;

import tasks.Event;
import tasks.Task;
import utility.Parser;
import utility.Ui;
import validation.TaskValidation;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class event {
    public static void executeEvent (String userInput, ArrayList<Task> tasks) {
        // Removes command word
        String withoutCommand = Parser.removeCommandWord(userInput);

        // Obtains description - name of the event task
        String description = Parser.getDescriptionForEvent(withoutCommand);

        if (!TaskValidation.isNull(description)) {
            return;
        }

        // Returns in the format [from, to]
        String[] timings = Parser.getTimings(withoutCommand);

        // Creates a new event, parsing the date and time
        Event event = new Event(description, LocalDateTime.parse(timings[0], Ui.FORMATTER).format(Ui.FORMATTER), LocalDateTime.parse(timings[1], Ui.FORMATTER).format(Ui.FORMATTER));
        tasks.add(event);

        // Prints acknowledgement
        Ui.printAcknowledgement("Event", description, String.valueOf(tasks.size()));
    }
}
