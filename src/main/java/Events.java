public class Events extends Task {
    protected String start;
    protected String end;
    public Events(String description, String startTime, String endTime) {
        super(description);
        start = startTime;
        end = endTime;
    }
    public String getIcon(){
        return StrIntLib.eventIcon;
    }
    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }
}
