package limey.command;
import limey.exception.invalidDateException;
public class Event extends Task{
    private String fromDate;
    private String toDate;
    public void setFromDate(String fromDate) {
        this.fromDate= fromDate;
    }
    public void setToDate(String toDate) {
        this.toDate= toDate;
    }
    public Event(String inLine) throws invalidDateException{
            super(inLine);
            if(!inLine.contains("/from") |  !inLine.contains("/to")) {
                throw new invalidDateException();
            }
            int indexOfFrom = inLine.indexOf("/from");
            int indexOfTo =  inLine.indexOf("/to");
            fromDate = inLine.substring(indexOfFrom + 5, indexOfTo);
            toDate = inLine.substring(indexOfTo + 3);
            setTaskName(getTaskName() + " (from " + getFromDate() + " to " + getToDate() + ")");
        }
    @Override
    public String getTaskIdentity() {
        String eventSymbol = "[E]";
        return eventSymbol + super.getTaskIdentity();
    }
    public String getFromDate(){
        return fromDate.trim();
    }
    public String getToDate(){
        return toDate.trim();
    }
}
