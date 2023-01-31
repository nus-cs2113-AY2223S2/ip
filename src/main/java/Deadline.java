public class Deadline extends Task {
    protected String endDate;
    public Deadline(String name, int ID, String endDate) {
        super(name, ID);
        this.label.setLabel("D");
        this.endDate = endDate;
    }

    /** Prints out the label, checkBox, name followed by the deadline */
    @Override
    public void printTaskName() {
        this.label.printLabel();
        this.checkBox.printCheckBox();
        System.out.print(this.getTaskName());
        System.out.println(" (Due: " + this.endDate + ")");
    }
}
