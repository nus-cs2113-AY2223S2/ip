package command;

import tasks.Deadline;
import tasks.Task;
import utility.Parser;
import utility.Ui;
import validation.TaskValidation;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class deadline {
    /**
     * Adds deadline based on user's input
     * @param userInput Deadline details
     * @param tasks Array list of tasks
     */
    public static void executeDeadline(String userInput, ArrayList<Task> tasks) {
        // Removes the command word
        String withoutCommand = Parser.removeCommandWord(userInput);

        // Obtains description - name of deadline task
        String description = Parser.getDescriptionForDeadline(withoutCommand);

        if (!TaskValidation.isNull(description)) {
            return;
        }

        // Obtains date/time deadline
        String by = Parser.getDeadline(withoutCommand);

        // Creates a new deadline, parsing the date and time
        Deadline deadline = new Deadline(description, LocalDateTime.parse(by, Ui.FORMATTER).format(Ui.FORMATTER));
        tasks.add(deadline);

        // Prints acknowledgement
        Ui.printAcknowledgement("Deadline", description, String.valueOf(tasks.size()));
    }
}
