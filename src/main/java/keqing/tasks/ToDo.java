package keqing.tasks;

import java.util.ArrayList;

public class ToDo extends Task {
    public static final String sign = "T";

    public ToDo(String description) {
        super(description);
        taskCount += 1;
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
        return "[T]" + super.toString();
    }

    /**
     *
     * @return the ArrayList of string containing the details of the deadline/event starting and ending time
     */
    @Override
    public ArrayList<String> returnAttribute() {
        return null;
    }
}