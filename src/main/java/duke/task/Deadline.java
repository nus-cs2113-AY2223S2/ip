package duke.task;

public class Deadline extends Task {
    public String deadlineDate;
    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public String getDeadline() {
        return this.deadlineDate;
    }

    @Override
    public String printTask() {
        return "[D]" + super.printTask() + "(by: " + getDeadline() + ")";
    }

}
