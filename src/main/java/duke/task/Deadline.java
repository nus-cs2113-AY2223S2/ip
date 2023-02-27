package duke.task;

public class Deadline extends Task {
    public Deadline(String description) {
        super(description);
        this.taskType = TaskType.DEADLINE;
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
