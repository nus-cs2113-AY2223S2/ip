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
        saveString += "D" + COMMA + isDone + COMMA + description + COMMA + deadline;
        return saveString;
    }
}
