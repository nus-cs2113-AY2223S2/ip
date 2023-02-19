package tasks;
import errors.InvalidEventException;
import translators.SpecialInputDateTranslator;

import java.time.LocalDate;

/**
 * Represents a task that happen during a specific period of time. An <code>Event</code> corresponds to
 * a task with both a start date and an end date e.g., <code>Event (from: start to: end)</code>.
 */
public class Event extends Task {
    public static final String EVENT_TIME_START_INDICATOR = "/from";
    public static final String EVENT_TIME_END_INDICATOR = "/to";
    public static final int EVENT_TIME_START_DIVIDER_LENGTH = 6;
    public static final int EVENT_TIME_END_DIVIDER_LENGTH = 4;
    public static final String EVENT_CLASS = "E";
    public static final String DONE_EVENT_INDICATOR = "[E] [X]";
    public static final String NOT_DONE_EVENT_INDICATOR = "[E] [ ]";
    private String itemName;
    private String startTime;
    private String endTime;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Creates a new event with a start date and an end date.
     *
     * @param itemName A String that contains the name, starting date and ending date of the task,
     *                 with the dates seperated by <code>/from</code> and <code>/to</code>.
     * @throws InvalidEventException If <code>itemName</code> is of an incorrect format.
     */
    public Event(String itemName) throws InvalidEventException {
        super(itemName);
        int indexOfStartTime = itemName.indexOf(EVENT_TIME_START_INDICATOR);
        int indexOfEndTime = itemName.indexOf(EVENT_TIME_END_INDICATOR);
        if (indexOfEndTime == -1 || indexOfStartTime == -1) {
            throw new InvalidEventException();
        }
        this.itemName = super.getItemName().substring(0, indexOfStartTime).trim();
        this.startTime = itemName.substring(indexOfStartTime + EVENT_TIME_START_DIVIDER_LENGTH, indexOfEndTime).trim();
        this.endTime = itemName.substring(indexOfEndTime + EVENT_TIME_END_DIVIDER_LENGTH).trim();
        if (SpecialInputDateTranslator.isInSpecialFormat(startTime)) {
            this.startDate = SpecialInputDateTranslator.convertToDateObject(startTime);
            this.startTime = SpecialInputDateTranslator.formatDate(startDate);
        }
        if (SpecialInputDateTranslator.isInSpecialFormat(endTime)) {
            this.endDate = SpecialInputDateTranslator.convertToDateObject(endTime);
            this.endTime = SpecialInputDateTranslator.formatDate(endDate);
        }
        incrementItemCount();
    }

    @Override
    public void printTask() {
        String status;
        if (super.isDone()) {
            status = DONE_EVENT_INDICATOR;
        } else {
            status = NOT_DONE_EVENT_INDICATOR;
        }
        System.out.println("." + status + " " + this.itemName +
                " (from: " + startTime + " to: " + endTime + ")");
    }

    @Override
    public void printTaskWithoutId() {
        System.out.println( "  [E] " + this.itemName +  " (from: " + startTime + " to: " + endTime + ")");
    }

    @Override
    public String getClassType () {
        return EVENT_CLASS;
    }

    @Override
    public String getToStore() {
        return (" " + this.itemName + " /from " + this.startTime + " /to " + this.endTime);
    }

}
