package Arsdorint.task;

import java.time.LocalDate;

public abstract class Task {
    // initial number of task must be set to 0
    public static final String VERTICAL_BAR = " | ";
    public static final String PARSE_LIMITER = "\\|";
    public static int numOfTasks = 0;
    public String description;
    public boolean isDone;
    public String taskType = "[ ]";
    public String taskName;
    public LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numOfTasks++;
    }

    /**
     * toString for viewing
     */
    public String toString() {
        return (this.taskType + this.getStatus() + " " + this.description);
    }

    /**
     * Get the status of the task ([X] as marked and [ ] as unmarked)
     *
     * @return [X] if the task is done, else [ ]
     */
    public String getStatus() {
        return (isDone) ? "[X]" : "[ ]";
    }

    /**
     * Show the result in binary format (1 and 0)
     *
     * @return true 1 if the task is done, else 0
     */
    public int binaryRes() {
        return (isDone) ? 1 : 0;
    }

    public String toSave() {
        return (this.taskName + VERTICAL_BAR + binaryRes() + VERTICAL_BAR + this.description + "\n");
    }

    /**
     * A boolean function to check if the date input is null or not
     *
     * @return true if the date is null
     */
    public boolean isDateNull() {
        return (this.date == null ? true : false);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setIsDone(boolean status) {
        this.isDone = status;
    }

    public String getType() {
        return this.taskName;
    }

    public boolean isDone() {
        return this.isDone;
    }
}

