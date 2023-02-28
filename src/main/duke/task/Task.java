package duke.task;

import java.time.LocalDate;

/**
 * Template for a task object.
 */
public class Task {
    protected String task;
    protected boolean isDone;
    protected String type;
    protected LocalDate localByDate;
    protected LocalDate localFromDate;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getLocalByDate() {
        return localByDate;
    }

    public LocalDate getLocalFromDate() {
        return localFromDate;
    }

    /**
     * Returns a string representation of marked/unmarked for a task object.
     *
     * @return <code>[ ]</code> if unmarked and <code>[X]</code> if marked.
     */
    public String checkDone() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    /**
     * Returns a string representation of type for a task object.
     *
     * @return <code>[ "type" ]</code>
     */
    public String checkType() {
        if (type != null) {
            return "[" + type + "]";
        }
        return "[ ]";
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Template for a task object with all of its parameters initialized.
     *
     * @param task Contains the task contents.
     * @param isDone Contains the task status marked/unmarked.
     */
    public Task(String task, boolean isDone) {
        setTask(task);
        setDone(isDone);
        localByDate = null;
        localFromDate = null;
    }

    /**
     * Returns a <code>String</code> representation of this task object.
     *
     * @return Returns the task object as a <code>String</code> in the form <code>"  " + type + isDone()
     * + " " + task</code>.
     */
    @Override
    public String toString() {
        String [] temp = this.getTask().split("%", 2);
        if (temp.length == 2) {
            return "  " + this.checkType() + this.checkDone() + " " + temp[0].trim();
        }
        return "  " + this.checkType() + this.checkDone() + " " + getTask();
    }

    public boolean getIsDone() {
        return this.isDone;
    }
}
