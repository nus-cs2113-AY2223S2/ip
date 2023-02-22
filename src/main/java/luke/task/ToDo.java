package luke.task;

/**
 * A <code>ToDo</code> object represents a task that has no deadline.
 */
public class ToDo extends Task {
    public ToDo(String name, int ID) {
        super(name, ID);
        this.label.setLabel("T");
    }

    /** Prints out the label followed by the checkBox followed by the name of the task */
    @Override
    public void printTaskName() {
        System.out.print(this.serialNumber + ". ");
        this.label.printLabel();
        this.checkBox.printCheckBox();
        System.out.println(this.getTaskName());
    }
}
