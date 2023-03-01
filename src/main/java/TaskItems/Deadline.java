package TaskItems;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Todos {

    protected String type = "D";
    public LocalDate dueBy;

    public Deadline(String name, boolean isMarked, String type, LocalDate dueBy) {

        super(name, isMarked, type);
        this.dueBy = dueBy;
    }
    /**
     * checks what type of task item it is
     * @return a string "[D]" indicating that it is an Deadline type
     */
    public String getType() {
        return "[D]";
    }
}