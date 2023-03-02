import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    LocalDate from;
    LocalDate to;

    Event(String content, LocalDate start, LocalDate end) {
        super(content);
        try{
            this.from = start;
            this.to = end;

        } catch (Exception e){
            UI.printDateFormatErrorComment();
        }
    }

    @Override
    public String toString() {
        String returnStr = "[E]";
        if (getIsDone()) {
            returnStr = returnStr.concat("[O] ");
        } else {
            returnStr = returnStr.concat("[ ] ");
        }

        return returnStr + getContents() + " / from " + from + " / to " + to;
    }
}
