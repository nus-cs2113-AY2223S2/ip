package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents task that has a deadline.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDateTime localBy;

    /**
     * Converter for <code>Deadline</code> task. It accepts an input in format of an instruction and 
     * returns the <code>Deadline</code> task object.
     * @param instruction Written in format of "deadline <code>description</code> /by <code>by</code>(deadline)", 
     * e.g. "deadline Return books /by 2023/03/31 18:00".
     * @return <code>Deadline</code> object constructed from input <code>instruction</code>.
     * @throws DukeException If 1) description is not given; 2) deadline is not given; 3) deadline is in wrong format.
     */
    public static Deadline toDeadline(String instruction) throws DukeException {
        int contentIdx = instruction.indexOf("/by");

        if (contentIdx == -1) {
            throw new DukeException("Please add time of the deadline.\n" + "    " +
                                    "(Event format: [Deadline Content] /by [Deadline])");
        }

        String deadlineContent = instruction.substring(0, contentIdx);

        if (deadlineContent.trim().equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String deadlineBy = instruction.substring(contentIdx + "/by ".length());
        
        if (deadlineBy.trim().equals("")) {
            throw new DukeException("The time of a deadline cannot be empty.");
        }

        return new Deadline(deadlineContent, deadlineBy);
    }

    /**
     * Constructor for <code>Deadline</code> task.
     * @param description Description of <code>Deadline</code> task, e.g. "Return books".
     * @param by Deadline of <code>Deadline</code> task, e.g. "2023/03/31 18:00".
     */
    public Deadline(String description, String by) {
        super(description, 'D');
        this.by = by;
        convertDateTime(by);
    }

    /**
     * Convert deadline time from <code>String</code> type to <code>LocalDateTime</code> type.
     * If <code>by</code> is not in the format of "yyyy/MM/dd HH:mm", converter will not work and 
     * set the result as <code>null</code>.
     * @param by Deadline of the task in <code>String</code> type.
     */
    private void convertDateTime(String by) {
        try {
            localBy = LocalDateTime.parse(by, parseFormatter);
        } catch(DateTimeParseException e) {
            localBy = null;
        }
    }

    @Override
    public String getTimeBound() {
        return by;
    }

    @Override
    public String toString() {
        if(localBy == null) {
            return super.toString() + "(by: " + by + ")";
        }else {
            return super.toString() + "(by: " + localBy.format(printFormatter) + ")";
        }
    }

    @Override
    public boolean haveValidDate() {
        return (localBy != null);
    }

    @Override
    public LocalDateTime getEndTime() {
        return localBy;
    }
}
