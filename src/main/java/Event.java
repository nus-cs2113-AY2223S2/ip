public class Event extends Task{
    String startDate;
    String endDate;
    public Event(String description, String startDate, String endDate) {
        super(description, TypeOfTask.EVENT);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the symbol for EVENT task
     * @return "E"
     */
    @Override
    public String getStatusForTypeOfTask() {
        return "E";
    }

    /**
     * Prints out the event according to a format
     */
    @Override
    public void printTask() {
        System.out.println(".[" + this.getStatusForTypeOfTask() + "]" +
                            "[" + this.getStatusIcon() + "] " +
                            this.getDescription() +
                            "(from: " + this.startDate + "to: " + this.endDate + ")");
    }

}
