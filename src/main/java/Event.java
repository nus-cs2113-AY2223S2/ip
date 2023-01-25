public class Event extends Deadline{
    private String startTime;


    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getStartTime(){
        return startTime;
    }

    public Event(String description) {
        super(description);
    }

    @Override
    public void printTask(){
        if (this.isCompleted){
            System.out.println(".[E][X]" + this.taskName + "(from: " +this.getStartTime() +" by: " + this.getEndTime() + ")");
        }else {
            System.out.println(".[E][ ]" + this.taskName + "(from: " + this.getStartTime() + " by: " + this.getEndTime() + ")");
        }
    }
}
