public class Event extends Deadline {
    protected String endDate;
    protected String startDate;
    public Event(String name, int ID, String startDate, String endDate) {
        super(name, ID, endDate);
        this.label.setLabel("E");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /** Prints out the label, checkBox, name followed by the deadline */
    @Override
    public void printTaskName() {
        this.label.printLabel();
        this.checkBox.printCheckBox();
        System.out.print(this.getTaskName());
        System.out.println(" (From: " + this.startDate + " to: " + this.endDate + ")");
    }
}
