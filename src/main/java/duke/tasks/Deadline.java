package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * A deadline object contains deadline tasks with their task descriptions and due date.
 */
public class Deadline extends Task{
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        LocalDate date = LocalDate.parse(by);
        this.by = date;
    }

    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Overwriting toString method to return the event task with '[D]' prefix and append it with its due date.
     *
     * @return
     */
    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Cover the deadline task data into appropriate format to be stored in data file.
     *
     * @return
     */
    public String convertToData(){
        String status;
        if(getTaskStatus().equals("X")){
            status = "1";
        }else{
            status = "0";
        }
        String data = "D|"+status+"|"+getTaskDescription()+"|"+getBy()+"\n";
        return data;
    }
}
