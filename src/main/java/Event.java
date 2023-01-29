public class Event extends Task{
    String startDate;
    String endDate;
    public Event(String description, int index, String startDate, String endDate) {
        super(description, index, TypeOfTask.EVENT);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void printTask() {
        System.out.println((this.index + 1) +
                            ".[" + this.getStatusForTypeOfTask() + "]" +
                            "[" + this.getStatusIcon() + "] " +
                            this.getDescription() +
                            "(from: " + this.startDate + "to: " + this.endDate + ")");
    }

}
