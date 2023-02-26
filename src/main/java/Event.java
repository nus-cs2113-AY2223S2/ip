public class Event extends Task{
    String startDate;
    String endDate;
    public Event(boolean isDone, String description, String startDate, String endDate) {
        super(TypeOfTask.EVENT, isDone, description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the symbol for EVENT task
     *
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
        System.out.println(formatString());
    }

    @Override
    public String formatString() {
        return (".[" + this.getStatusForTypeOfTask() + "]" +
                "[" + this.getStatusIcon() + "] " +
                this.getDescription() +
                "(from: " + this.startDate + "to: " + this.endDate + ")");
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
