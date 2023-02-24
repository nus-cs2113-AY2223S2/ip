package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime localBy;

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

    public Deadline(String description, String by) {
        super(description, 'D');
        this.by = by;
        convertDateTime(by);
    }

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
