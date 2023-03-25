/**
 * Event class that contains type, status, timeFrame and thingToDo that describe the task
 */

package duke.Tasks;

import duke.Tasks.Task;

public class Event extends Task {
    protected String timeFrame;
    protected String thingToDo;
    public Event(String type, String status, String description) {
        super(type, status, description);
        int index = description.indexOf("/");
        timeFrame = description.substring(index + 1);
        thingToDo = description.substring(0, index - 1);
    }

    public String getTimeFrame() {
        return timeFrame;
    }

    /**
     * Returns a string displaying the Event task details
     * @return printFormat in the form of [E] [ ] task (time frame)
     */
    @Override
    public String getPrintFormat() {
        return type + " " + status + " " + thingToDo + " (" + timeFrame + ")";
    }
}
