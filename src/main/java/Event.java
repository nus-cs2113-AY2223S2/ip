public class Event extends Task {

    private String beginDate;
    private String endDate;

    public Event(String content, String beginDate, String endDate) {
        super(content);
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    @Override
    public String getClassSymbol() {
        return "E";
    }

    @Override
    public String getBeginDate() {
        return this.beginDate;
    }

    @Override
    public String getEndDate() {
        return this.endDate;
    }

}
