package luke.icon;

/**
 * A <code>CheckBox</code> object is use to indicate if a specific task have been marked as complete or incomplete.
 */
public class CheckBox {
    /** Visual representation of the checkbox */
    private String checkBox;

    public CheckBox() {
        checkBox = "[ ]";
    }

    /** Change the state of the checkbox from unmarked to marked */
    public void markCheckBox() {
        checkBox = "[X]";
    }

    /** Change the state of the checkbox from marked to unmarked */
    public void unmarkCheckBox() {
        checkBox = "[ ]";
    }

    /** Prints out the checkbox */
    public void printCheckBox() {
        System.out.print(checkBox + " ");
    }
}
