package Duke;

public final class Event extends Deadline {
    public String startTime;


    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public Event(String description) {
        super(description);
    }

    public void newEventResponse() {
        System.out.println("Got it. I've added \"" + this.taskName +"\"");
        System.out.println("with a start time of: " + this.getStartTime());
        System.out.println("and an ending time of: " + this.getEndTime());


    }

    @Override
    public void printTask() {
        if (this.isCompleted) {
            System.out.println(".[" + getTaskType(this) + "][X]" + this.taskName + "(from: " + this.getStartTime() + " to: " + this.getEndTime() + ")");
        } else {
            System.out.println(".[" + getTaskType(this) + "][ ]" + this.taskName + "(from: " + this.getStartTime() + " to: " + this.getEndTime() + ")");
        }
    }

    public void setAsEvent(){
        this.taskType = "E";
    }
    @Override
    public String getTaskType (Task task){
        return this.taskType;
    }

}
