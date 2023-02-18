package tasks;
import enums.DialogueTypes;
import errors.InvalidDeadlineException;
import managers.OutputDialogueManager;

public class Deadline extends Task {
    public static final int DEADLINE_DIVIDER_LENGTH = 4;
    public static final String DEADLINE_SEPARATOR = "/by";
    public static final String DONE_DEADLINE_INDICATOR = "[D] [X]";
    public static final String NOT_DONE_DEADLINE_INDICATOR = "[D] [ ]";
    public static final String DEADLINE_CLASS = "D";
    private String itemName;
    private String deadline;

    public Deadline(String itemName) throws InvalidDeadlineException {
        super(itemName);
        int indexOfDivider = itemName.indexOf(DEADLINE_SEPARATOR);
        if (indexOfDivider == -1 ||
                itemName.substring(indexOfDivider + DEADLINE_DIVIDER_LENGTH).isEmpty()) {
            throw new InvalidDeadlineException();
        }
        this.itemName = super.getItemName().substring(0,indexOfDivider);
        this.deadline = itemName.substring(indexOfDivider + DEADLINE_DIVIDER_LENGTH);
        incrementItemCount();
    }

    @Override
    public void printTask() {
        String status;
        if (super.isDone()) {
            status = DONE_DEADLINE_INDICATOR;
        } else {
            status = NOT_DONE_DEADLINE_INDICATOR;
        }
        System.out.println( "." + status + " " + this.itemName + " (by: " + deadline + ")");
    }

    @Override
    public void printTaskWithoutId() {
        System.out.println( "  [D] " + this.itemName + " (by: " + deadline + ")");
    }

    @Override
    public String getClassType() {
        return DEADLINE_CLASS;
    }

    @Override
    public String getToStore() {
        return (" " + this.itemName + " /by " + this.deadline);
    }
}
