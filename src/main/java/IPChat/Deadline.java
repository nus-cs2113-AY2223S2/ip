package IPChat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class which is used for creating tasks with deadlines
 *
 * @author DeepanjaliDhawan
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor which is used to instantiate the deadline object
     *
     * @param description Description of the task 'deadline'
     * @param by The deadline due time
     */
    public Deadline (String description, String by) {
        super(description);
        LocalDate time = LocalDate.parse(by);
        this.by = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Method to return the string in the ArrayList
     * @return String in the ArrayList
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.toString() + "(by: " + by + ")";
    }

    /**
     * Method to save the deadline in the file
     * @return deadline description with the by time
     */
    @Override
    public String saveStuff () {
        int save = 0;
        if (this.isDone) {
            save = 1;
        }
        return "deadline" + description + "/by" + by + " | " + save;
    }
}
