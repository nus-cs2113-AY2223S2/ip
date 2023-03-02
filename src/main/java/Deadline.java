import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    LocalDate by;

    Deadline(String content, LocalDate end) {
        super(content);
        try{
            this.by = end;
        } catch(Exception e){
            UI.printDateFormatErrorComment();
        }
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
}
