public class CheckBox {
    private String checkBox;
    public CheckBox() {
        checkBox = "[ ]";
    }

    public void markCheckBox() {
        checkBox = "[X]";
    }

    public void unmarkCheckBox() {
        checkBox = "[ ]";
    }

    public void printCheckBox() {
        System.out.print(checkBox + " ");
    }
}
