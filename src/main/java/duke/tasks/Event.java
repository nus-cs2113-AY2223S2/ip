package duke.tasks;

/**
 * An event object contains event tasks with their task descriptions and period(start and end time).
 */
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

    /**
     * Overwriting toString method to return the event task with '[E]' prefix and append it with its period.
     *
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    /**
     * Cover the event task data into appropriate format to be stored in data file.
     *
     * @return
     */
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
