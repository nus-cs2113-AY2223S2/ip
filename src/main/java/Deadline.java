import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
//import java.time.temporal.ChronoUnit;

/*
 * Sub-class of super-class <code>Task</code>, represents a task that has a deadline,
 * Contains attributes due, info, deadline name and evaluates attribute whether Deadline task is overdue
 */
public class Deadline extends Task {
    public LocalDate due;
    public String dueText;
    public String[] info;
    public String deadlineName;
    public boolean isOverdue;

    public Deadline(String description) {
        super(description);
        this.info = this.description.split("/by", 2);
        this.deadlineName = info[0];
        this.due = isValid((info[1]).trim());
        if (this.due == null) {
            this.dueText = (info[1]).trim();
            System.out.println("Due date is not in yyyy-MM-dd format, will be saved as text");
        } else if (this.due.isBefore(LocalDate.now())) {
            this.isOverdue = true;
        }

    }

    private LocalDate isValid(String date) {
        try {
            return LocalDate.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String fileFormat() {
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (this.due != null) {
            return (String.format("D|%b|%s /by %s\n", super.isDone, this.deadlineName,
                    due.format(yyyyMMdd)));
        } else {
            return (String.format("D|%b|%s /by %s\n", super.isDone, this.deadlineName, this.dueText));
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (this.due != null) {
            return ("[D][" + super.getStatusIcon() + "] " + this.deadlineName) +
                    " (by: " + this.due.format(yyyyMMdd) + ")";
        } else {
            return ("[D][" + super.getStatusIcon() + "] " + this.deadlineName) +
                    " (by: " + this.dueText + ")";
        }
    }
}
