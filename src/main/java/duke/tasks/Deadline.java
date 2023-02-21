package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

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
