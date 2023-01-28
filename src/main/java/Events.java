public class Events extends Task {

    protected String startTime;
    protected String endTime;

    public Events (String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getTypeOfTask() {
        return "E";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
