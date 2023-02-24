public class Events extends Task {
    protected String start;
    protected String end;
    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    public String getIcon(){
        return "E";
    }
    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }
    public void setStart(String newStart) {
        start = newStart;
    }
    public void setEnd(String newEnd) {
        end = newEnd;
    }
}
