package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The <code>Deadline</code> object represents the task with a deadline that the user adds.
 * It contains a <code>LocalDate</code> object that stores the date.
 */
public class Deadline extends Task {

    protected LocalDate date;

    /**
     * The class constructor that sets the description of the deadline.
     *
     * @param description The description of the deadline.
     */
    public Deadline(String description) {
        super(description);
        this.taskType = TaskType.DEADLINE;
        this.date = null;
    }

    /**
     * Formats the deadline into a specific String.
     * It also reformats the date if possible.
     *
     * @return A formatted String representing the deadline.
     */
    @Override
    public String toString() {
        String[] descriptionParts = this.description.split("/by");
        String descriptionDetails = descriptionParts[0];
        String descriptionDeadline = descriptionParts[1].trim();
        String descriptionOutput;

        try {
            this.date = LocalDate.parse(descriptionDeadline);
            descriptionOutput = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            descriptionOutput = descriptionDeadline;
        }

        return "[" + this.getType() + "][" + this.getStatusIcon() + "] " +
                descriptionDetails + "(by: " + descriptionOutput + ")";
    }
}
