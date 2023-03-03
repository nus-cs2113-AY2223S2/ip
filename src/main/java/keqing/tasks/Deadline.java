package keqing.tasks;

import java.util.ArrayList;

public class Deadline extends Task {
    protected String by;

    public static final String sign = "D";

    /**
     * Constructs the deadline object.
     *
     * @param description the details of the deadline task
     * @param by the exact deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        Task.taskCount += 1;
    }

    /**
     *
     * @return a string indicating the type of the task
     */
    @Override
    public String getTaskType() {
        return sign;
    }

    /**
     *
     * @return a string indicating the detailed information of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     *
     * @return the ArrayList of string containing the details of the deadline/event starting and ending time
     */
    public ArrayList<String> returnAttribute() {
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add(by);
        return attributes;
    }
}