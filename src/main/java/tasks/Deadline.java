package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate deadlineDate){
        super(description);
        this.by = deadlineDate;
    }

    public String convertPrintDateFormat(LocalDate by){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return by.format(formatter);
    }

    public String convertEncodeDateFormat(LocalDate by){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return by.format(formatter);
    }

    @Override
    public String printTask(){
        return "[D] " + super.printTask() + " (by: "+ convertPrintDateFormat(by) + ")";
    }

    public String encode(){
        String encodedString = "deadline" + "/" + super.encode() + "/" + convertEncodeDateFormat(by);  
        return encodedString;
    }

}


