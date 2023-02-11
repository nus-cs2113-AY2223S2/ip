package duke.tasks;

public class Event extends Task{
    protected String startTime;
    protected String endTime;

    public Event(String description, String start, String end) {
        super(description);
        this.startTime = start;
        this.endTime = end;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    public String convertToData(){
        String status;
        if(getTaskStatus().equals("X")){
            status = "1";
        }else{
            status = "0";
        }
        String data = "E|"+status+"|"+getTaskDescription()+"|"+getStartTime()+"|"+getEndTime()+"\n";
        return data;
    }
}
