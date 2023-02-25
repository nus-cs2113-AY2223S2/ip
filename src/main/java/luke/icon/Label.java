package luke.icon;

/**
 * A <code>Label</code> object is use to indicate the type of the task.
 * [T] corresponds to a <code>todo</code> task.
 * [D] corresponds to a <code>deadline</code> task.
 * [E] corresponds to a <code>event</code> task.
 */
public class Label {
    /** Visual representation of the task label */
    private String label;

    public Label() {
        label = "[ ]";
    }

    /** Fills in the label with a marking that corresponds to the type of task */
    public void setLabel(String labelMark) {
        label = String.format("[" + labelMark + "]");
    }

    /** Prints out the label */
    public void printLabel() {
        System.out.print(label);
    }

    /** Returns the label */
    public String getLabel() {
        return this.label;
    }
}
