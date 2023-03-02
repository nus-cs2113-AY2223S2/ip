import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
//import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    public LocalDate due;
    public String[] info;
    public String deadlineName;
    public boolean isOverdue;

    public Deadline(String description) {
        super(description);
        this.info = this.description.split("/by", 2);
        this.deadlineName = info[0];
        this.due = LocalDate.parse(info[1]);
        this.isOverdue = due.isAfter(LocalDate.now());
    }

    @Override
    public String fileFormat() {
        return (String.format("D|%b|%s by %s", super.isDone, this.deadlineName,
                due.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))));
    }

    @Override
    public String toString() {
        return ("[D][" + super.getStatusIcon() + "] " + this.deadlineName) + " (by:  " + this.due + ")";
    }
}
