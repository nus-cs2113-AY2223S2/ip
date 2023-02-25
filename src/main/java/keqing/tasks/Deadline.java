package keqing.tasks;

import java.util.ArrayList;

public class Deadline extends Task {
    protected String by;

    public static final String sign = "D";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        Task.taskCount += 1;
    }

    @Override
    public String getTaskType() {
        return sign;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public ArrayList<String> returnAttribute() {
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add(by);
        return attributes;
    }
}