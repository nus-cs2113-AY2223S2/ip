public class Deadline extends Todo {
    protected String by;
    String deliverable;

    public Deadline(String inputContents) {
        super(inputContents);
        String[] parts = inputContents.split("/by");
        String deliverable = parts[0];
        this.by = (parts.length > 1) ? parts[1].trim() : "";
        this.deliverable = deliverable;
        this.type = "D";

    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return deliverable + System.lineSeparator() + "(by: " + by + ")";
    }
}
