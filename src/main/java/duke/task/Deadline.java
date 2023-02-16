package duke.task;

public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.taskString = saveTaskString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String saveTaskString() {
        String saveString = new String();
        saveString += "D" + COMMA_TASK_SEPARATOR + isDone + COMMA_TASK_SEPARATOR + description + COMMA_TASK_SEPARATOR
                + deadline;
        return saveString;
    }
}
