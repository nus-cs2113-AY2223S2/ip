package Duke;
public class DukeEvents extends DukeTasks{
    protected String fromDetail;
    protected String toDetail;
    
    /**
     * Creates a new event task recorded by Duke and sets its marked status to false. 
     * Sets start and end time using arguments. 
     * 
     * @param description Description of the task
     * @param durationDetail Start and end time of the task, split by '/to'
     */
    public DukeEvents(String description, String durationDetail) {
        super(description);
        String[] fromToDetail = durationDetail.split("/to", 2);
        this.fromDetail = fromToDetail[0];
        this.toDetail = fromToDetail[1];
    }
    
    /**
     * Returns start time of event task
     * 
     * @return Start time of event task
     */
    public String getFromDetail() {
        return fromDetail;
    }
    
    /**
     * Sets start time of event task to this argument
     * 
     * @param fromDetail Updated start time of event task
     */
    public void setFromDetail(String fromDetail) {
        this.fromDetail = fromDetail;
    }
    
    /**
     * Returns end time of event task
     * 
     * @return End time of event task
     */
    public String getToDetail() {
        return toDetail;
    }
    
    /**
     * Sets end time of event task to this argument
     * 
     * @param toDetail Updated end time of event task
     */
    public void setToDetail(String toDetail) {
        this.toDetail = toDetail;
    }

    @Override
    public String getTaskType() {
        return ("E");
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + this.fromDetail + ", to: " + this.toDetail + ")";
    }
}
