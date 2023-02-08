public class Dateline extends Tasks {

    String dueDate;

    public Dateline(String item, boolean isMarked, String dueDate) {
        super(item, isMarked);
        this.dueDate = dueDate.replace("by", "by:");
    }

    public String getDueDate() {

        return dueDate;
    }

    public void setDueDate(String dueDate) {

        this.dueDate = dueDate;
    }
    public String toString() {
        return "[D]" + super.toString() + "(" + dueDate + ")";
    }
}
