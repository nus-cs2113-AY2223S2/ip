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
        String descriptionDeadline = descriptionParts[1];

        return "[" + this.getType() + "][" + this.getStatusIcon() + "] " +
                descriptionDetails + "(by:" + descriptionDeadline + ")";
    }

}
