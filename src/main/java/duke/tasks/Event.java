/**
 * Contains methods to build up a complete Task
 *
 * @param description A string that details the description of the task
 * @param isDone boolean variable that identifies whether the task is completed
 * @param event A string that identifies the Task as an event task
 * @param startAndEnd A string that represents the duration of the event
 */

package duke.tasks;

import duke.Task;

public class Event extends Task {

    protected String event;
    protected String startAndEnd;

    public Event(String description, boolean isDone, String event, String startAndEnd) {
        super(description, isDone);
        this.event = event;
        this.startAndEnd = startAndEnd;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getStartAndEnd() {
        return startAndEnd;
    }

    public void setStartAndEnd(String startAndEnd) {
        this.startAndEnd = startAndEnd;
    }

}
