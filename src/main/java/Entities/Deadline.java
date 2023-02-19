package Entities;

import java.time.LocalDateTime;
import EntityUtils.DateParser;

/**
 * Task which has a do-by date
 */
public class Deadline extends Task {
    protected LocalDateTime endDate;

    /**
     * Constructor for Deadline Class
     * @param taskDescription task details
     * @param isDone whether task is completed
     * @param endDate date by which task should be completed
     */
    public Deadline(String taskDescription, boolean isDone, LocalDateTime endDate) {
        super(taskDescription, isDone);
        setEndDate(endDate);
    }

    /**
     * Gets deadline of task
     * @return deadline of task
     */
    public String getEndDate() {
        return DateParser.dateToString(endDate);
    }

    /**
     * Sets deadline of task
     * @param endDate new deadline of task
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * Defines how Deadline should be printed
     * @return string format of Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getEndDate() + ")";
    }

}
