public class Event extends Task{
    private String fromDate;
    private String toDate;
    public Event(String inLine){
            super(inLine);
            int indexOfFrom = inLine.indexOf("/from");
            int indexOfTo =  inLine.indexOf("/to");
            fromDate = inLine.substring(indexOfFrom + 5, indexOfTo);
            toDate = inLine.substring(indexOfTo + 3);
            setTaskName(getTaskName() + " (from " + getFromDate() + " to " + getToDate() + ")");
        }
    @Override
    public String getTaskIdentity() {
        String todoSymbol = "[E]";
        return todoSymbol + super.getTaskIdentity();
    }
    public String getFromDate(){
        return fromDate.trim();
    }
    public String getToDate(){
        return toDate.trim();
    }
}
