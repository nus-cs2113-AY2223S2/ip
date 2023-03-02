/**
 * Contains methods to build up a complete Task
 *
 * @param description A string that details the description of the task
 * @param isDone boolean variable that identifies whether the task is completed
 * @param deadline A string that identifies the Task as a Deadline task
 * @param date A string that represents the deadline date
 */

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
