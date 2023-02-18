package duke.tasks;

import duke.Task;

public class Deadline extends Task {

    protected String deadline;

    protected String date;
    public Deadline(String description, boolean isDone, String deadline, String date) {
        super(description, isDone);
        this.deadline = deadline;
        this.date = date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
