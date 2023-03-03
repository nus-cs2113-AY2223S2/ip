package Tasks;

public class Event extends Task {

    protected String startTime;
    
    protected String endTime;


    /**
     * This is the constructor of an object of class Event. It also
     * calls the default constructor of the class it has inherited (Task).
     *
     * @param taskName: name of the task
     * @param startTime: start time of the task
     * @param endTime: end time of the task
     */
    public Event(String taskName, String startTime, String endTime){
        super(taskName+ " (from: " + startTime + " to: " + endTime + ")", false, "[E]");
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString(){
        return super.toString();
    }

}