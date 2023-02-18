package tasks;
import enums.DialogueTypes;
import errors.InvalidEventException;
import managers.OutputDialogueManager;

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
    public Event(String itemName) throws InvalidEventException {
        super(itemName);
        int indexOfStartTime = itemName.indexOf(EVENT_TIME_START_INDICATOR);
        int indexOfEndTime = itemName.indexOf(EVENT_TIME_END_INDICATOR);
        if (indexOfEndTime == -1 || indexOfStartTime == -1) {
            throw new InvalidEventException();
        }
        this.itemName = super.getItemName().substring(0, indexOfStartTime).trim();
        this.startTime = itemName.substring(indexOfStartTime + EVENT_TIME_START_DIVIDER_LENGTH, indexOfEndTime);
        this.endTime = itemName.substring(indexOfEndTime + EVENT_TIME_END_DIVIDER_LENGTH);
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
                " (from: " + startTime + "To: " + endTime + ")");
    }

    @Override
    public void printTaskWithoutId() {
        System.out.println( "  [E] " + this.itemName +  " (from: " + startTime + "To: " + endTime + ")");
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
