public class Deadline extends Task {
    protected String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    public String toString() {
        return super.toString() + "(by: " + by +")";
    }

    public void readTask(String text) {
        super.readTask(text);
        this.description = text.substring(7, text.indexOf("(from: ")-1);
        this.by = text.substring(text.indexOf("(by: ")+1,text.indexOf(")")-1);
    }
}