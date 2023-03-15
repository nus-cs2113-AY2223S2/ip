package max.task;

import max.ui.Ui;

/**
 * Event is a subclass of Task
 * <p>
 * It additionally stores the from and to dates of the event.
 */
public class Event extends Task {
    private static final String TASK_LABEL = "E";
    private static final String WRAPPER_FROM = " (FROM: ";
    private static final String WRAPPER_COMMA = ", ";
    private static final String WRAPPER_TO = "TO: ";
    private static final String WRAPPER_END = ")";
    private Ui ui;
    private String eventFrom;
    private String eventTo;

    /**
     * Create an Event object, a subclass of Task.
     * Used to keep track of a Task's start and end times.
     *
     * @param description String describing the Event
     * @param from        Datetime of the event's start
     * @param to          Datetime of the event's end
     */
    public Event(String description, String from, String to) {
        super(description);
        this.eventFrom = from;
        this.eventTo = to;
        ui = new Ui();
    }

    @Override
    public String getDescription() {
        String desc = ui.wrapMessage(getTaskLabel()) + super.getDescription();
        String fromStr = WRAPPER_FROM + eventFrom + WRAPPER_COMMA;
        String toStr = WRAPPER_TO + eventTo + WRAPPER_END;
        desc = desc.concat(fromStr).concat(toStr);
        return desc;
    }

    @Override
    public String getTaskLabel() {
        return TASK_LABEL;
    }

    /**
     * Helper method to retrieve the 'from' date of an Event
     *
     * @return Event 'from' date
     */
    public String getEventFrom() {
        return eventFrom;
    }

    /**
     * Helper method to retrieve the 'to' date of an Event
     *
     * @return Event 'to' date
     */
    public String getEventTo() {
        return eventTo;
    }
}
