public class Dateline extends Tasks{

    String dueDate;
    public Dateline(String item, boolean isMarked, String dueDate) {
        super(item,isMarked);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
