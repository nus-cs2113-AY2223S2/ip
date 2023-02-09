public class Event extends Deadline {
    private String startTime;


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
            System.out.println(".[E][X]" + this.taskName + "(from: " + this.getStartTime() + " to: " + this.getEndTime() + ")");
        } else {
            System.out.println(".[E][ ]" + this.taskName + "(from: " + this.getStartTime() + " to: " + this.getEndTime() + ")");
        }
    }
    @Override
    public void setTaskType(Task type){
        this.taskType = "E";
    }
    @Override
    public String getTaskType (Task task){
        return this.taskType;
    }
}
