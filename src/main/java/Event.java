public class Event extends Tasks {
    String startDate;
    String endDate;

    public Event(String item, boolean isMarked, String startDate, String endDate) {
        super(item, isMarked);
        this.startDate = startDate.replace("from", "from:");
        this.endDate = endDate.replace("to", "to:");
    }

    public String getStartDate() {

        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate.replace("from", "from:");

    }

    public String getEndDate() {

        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate.replace("to", "to:");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + startDate + endDate + ")";
    }
}
