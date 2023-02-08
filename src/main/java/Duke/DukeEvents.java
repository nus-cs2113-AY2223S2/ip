package Duke;
public class DukeEvents extends DukeTasks{
    protected String fromDetail;
    protected String toDetail;
    
    public DukeEvents(String description, String durationDetail) {
        super(description);
        String[] fromToDetail = durationDetail.split("/to", 2);
        this.fromDetail = fromToDetail[0];
        this.toDetail = fromToDetail[1];
    }
    
    public String getFromDetail() {
        return fromDetail;
    }
    
    public void setFromDetail(String fromDetail) {
        this.fromDetail = fromDetail;
    }
    
    public String getToDetail() {
        return toDetail;
    }
    
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
