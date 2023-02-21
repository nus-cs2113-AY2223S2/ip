package duke.task;

public class Event extends Task{
    private String startDate;
    private String endDate;

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
    public Event(String command){
        super();
        setInitCommand(command);
        setDescription(command.substring(command.indexOf(" "), command.indexOf("/from")).trim());
        setStartDate(command.substring(command.indexOf("/from") + 5, command.indexOf("/to")).trim());
        setEndDate(command.substring(command.indexOf("/to") + 3).trim());
    }
    public Event(String description, String startDate, String endDate){
        super(description);
        this.startDate=startDate;
        this.endDate=endDate;
    }
    @Override
    public String toString(){
        return "[E][" + (isDone() ? 'X' : ' ') + "]//////" + super.toString() + " (from: " + getStartDate()
                + " to: " + getEndDate() + ')';
    }
}
