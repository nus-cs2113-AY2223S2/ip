package tasks;
import errors.InvalidDeadlineException;
import translators.SpecialInputDateTranslator;

import java.time.LocalDate;

/**
 * Represents a task with a deadline. A <code>Deadline</code> object represents
 * a task that have an end date.
 */
public class Deadline extends Task {
    public static final int DEADLINE_DIVIDER_LENGTH = 4;
    public static final String DEADLINE_SEPARATOR = "/by";
    public static final String DONE_DEADLINE_INDICATOR = "[D] [X]";
    public static final String NOT_DONE_DEADLINE_INDICATOR = "[D] [ ]";
    public static final String DEADLINE_CLASS = "D";
    private String itemName;
    private String deadline;

    private LocalDate deadlineDate;

    /**
     * Construct a Deadline Object from a line of information.
     *
     * @param itemName A String containing both name of the task and the ending time of
     *                 the task, seperated by <code>/by</code>.
     * @throws InvalidDeadlineException If the format of the line is incorrect.
     */
    public Deadline(String itemName) throws InvalidDeadlineException {
        super(itemName);
        int indexOfDivider = itemName.indexOf(DEADLINE_SEPARATOR);
        if (indexOfDivider == -1 ||
                itemName.substring(indexOfDivider + DEADLINE_DIVIDER_LENGTH).isEmpty()) {
            throw new InvalidDeadlineException();
        }
        this.itemName = super.getItemName().substring(0,indexOfDivider).trim();
        this.deadline = itemName.substring(indexOfDivider + DEADLINE_DIVIDER_LENGTH).trim();
        if (SpecialInputDateTranslator.isInSpecialFormat(deadline)) {
            this.deadlineDate = SpecialInputDateTranslator.convertToDateObject(deadline);
            this.deadline = SpecialInputDateTranslator.formatDate(deadlineDate);
        }
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
