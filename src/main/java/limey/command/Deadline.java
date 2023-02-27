package limey.command;

import limey.exception.invalidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private java.time.LocalDateTime dueDate;
    private String inDate;

    public String getDueDate() {
        return dueDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }

    public String getInDate() {
        return inDate;
    }

    public void setDueDate(String byDate) throws DateTimeParseException {
        this.dueDate = LocalDateTime.parse(byDate.trim());
    }
    /**
     * Creates a new deadline object with the proper name and due date if a valid date is given
     *
     * @param inLine user input without the command term (ie just the deadline name and its due date)
     */
    public Deadline(String inLine) throws invalidDateException {
        super(inLine);
        if (!inLine.contains("/by")) {
            throw new invalidDateException();
        }
        int indexOfBy = inLine.indexOf("/by");
        inDate = inLine.substring(indexOfBy + 3);
        try {
            setDueDate(inDate);
        } catch (DateTimeParseException e) {
            throw new invalidDateException();
        }
        setTaskName(super.getTaskName() + " (by: " + getDueDate() + ")");
    }

    @Override
    public String getTaskIdentity() {
        String todoSymbol = "[D]";
        return todoSymbol + super.getTaskIdentity();
    }

}
