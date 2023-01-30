public class Deadline extends Task {
    public String dueBy;

    public Deadline (String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    //@Override
    public String toString() {
      String newString = super.toString().replaceFirst("B","T");
      return newString + " (by: " + dueBy + ")";
    }

}
