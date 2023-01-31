public class Label {
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
}
