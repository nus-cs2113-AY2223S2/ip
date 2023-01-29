public class Deadline extends Task{

    protected String day;

    public Deadline(String description, String day) {
        super(description);
        this.day = day;
    }

    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String printTask() {
        return super.printTask() + "(by: " + day + ")";
    }

}
