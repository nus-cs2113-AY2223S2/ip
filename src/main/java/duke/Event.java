package duke;

/**
 * Event class represents an event task with a start time and a finish time.
 * It extends the Task class and adds the startTime and finishTime attributes.
 */
public class Event extends Task {
    protected String startTime;
    protected String finishTime;

    /**
     * Constructs an Event object with a name, completion status, start time, and
     * finish time.
     * 
     * @param name       the name of the event task
     * @param isDone     the completion status of the event task
     * @param startTime  the start time of the event task
     * @param finishTime the finish time of the event task
     */
    public Event(String name, boolean isDone, String startTime, String finishTime) {
        super(name, isDone);
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    /**
     * Returns a string representation of the event task with its completion status,
     * name, start time, and finish time.
     * If the task is completed, the completion status is marked with an 'X',
     * otherwise it is marked with a space.
     * 
     * @return a string representation of the event task
     */
    public String toString() {
        if (this.getIsDone() == true) {
            return " [E][X] " + this.getName() + " (from: " + this.startTime
                    + " to: " + this.finishTime + ")";
        }
        return " [E][ ] " + this.getName() + " (from: " + this.startTime
                + " to: " + this.finishTime + ")";
    }

    /**
     * Prints the string representation of the event task with a dot prefix.
     */
    public void print() {
        if (this.isIsDone() == false) {
            System.out.println("." + this.toString());
        } else {
            System.out.println("." + this.toString());
        }
    }
}
