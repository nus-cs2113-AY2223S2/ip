public class Event extends Tasks{
    String startDate;
    String endDate;

    public Event(String item, boolean isMarked, String startDate, String endDate) {
        super(item,isMarked);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
