public class Deadline extends Task{

    protected String day;
    public Deadline(String description, String dateAndTime) {
        super(description);
        this.day = dateAndTime;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + day + ")";
    }
    @Override
    public String getUpdate() {
        return "deadline " + super.description + "/by " + day;
    }
}
