package keqing.tasks;

import java.util.ArrayList;

public class Event extends Task {
    protected String from;
    protected String to;

    public static final String sign = "E";

    public Event (String description, String from, String to) {
        super(description);
        Task.taskCount += 1;
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the type of tasks.
     *
     * @return the string indicating the type of the task
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
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     *
     * @return the ArrayList of string containing the details of the deadline/event starting and ending time
     */
    @Override
    public ArrayList<String> returnAttribute() {
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add(from);
        attributes.add(to);
        return attributes;
    }
}