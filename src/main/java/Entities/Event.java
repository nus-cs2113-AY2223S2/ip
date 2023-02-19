package Entities;

import java.time.LocalDateTime;
import EntityUtils.DateParser;

/**
 * Task which has a start and end date
 */
public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Constructor for Event Class
     * @param taskDescription task details
     * @param isDone whether task is completed
     * @param startDate date when event begins
     * @param endDate date when event ends
     */
    public Event(String taskDescription, boolean isDone, LocalDateTime startDate, LocalDateTime endDate) {
        super(taskDescription, isDone);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    /**
     * Gets start date of task
     * @return start date of task
     */
    public String getStartDate() {
        return DateParser.dateToString(this.startDate);
    }

    /**
     * Sets start date of task
     * @param startDate start date of task
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date of task
     * @return end date of task
     */
    public String getEndDate() {
        return DateParser.dateToString(this.endDate);
    }

    /**
     * Sets end date of task
     * @param startDate end date of task
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * Defines how Event should be printed
     * @return string format of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + getStartDate() + " - " + getEndDate() + ")";
    }

}
