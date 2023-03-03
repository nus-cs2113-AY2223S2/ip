import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 * A <code>Deadline</code> object corresponds to a task with contents, task progress, and deadline.
 */

public class Deadline extends Task {
    private LocalDate by;

    Deadline(String content, LocalDate end) {
        super(content);
        try{
            this.by = end;
        } catch(Exception e){
            UI.printDateFormatErrorComment();
        }
    }

    public LocalDate getBy(){
        return by;
    }

    @Override
    public String toString() {
        String returnStr = "[D]";
        if (getIsDone()) {
            returnStr = returnStr.concat("[O] ");
        } else {
            returnStr = returnStr.concat("[ ] ");
        }

        return returnStr + getContents() + " / by " + by;
    }

    /**
     * Returns information of a <code>Deadline</code> object with dates in mm-dd-yyyy format.
     * @param none.
     * @return String.
     */
    @Override
    public String showTask(){
        String returnStr = "[D]";
        if (getIsDone()) {
            returnStr = returnStr.concat("[O] ");
        } else {
            returnStr = returnStr.concat("[ ] ");
        }

        return returnStr + getContents() + " / by " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
