package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description) {
        super(description);
        this.taskType = TaskType.DEADLINE;
        this.date = null;
    }

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
