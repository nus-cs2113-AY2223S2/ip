public class Deadline extends Task{

    protected String day;
    public Deadline(String description, String dateAndTime) {
        super(description);
        this.day = dateAndTime;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString() + "(by: " + day + ")";
    }
}
