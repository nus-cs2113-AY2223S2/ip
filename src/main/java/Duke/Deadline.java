package Duke;

public class Deadline extends Task {


    public String endTime;

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Deadline(String description) {
        super(description);
    }

    public void newDeadlineAction() {
        System.out.println("Got it. I've added \"" + this.taskName +"\"");
        System.out.println("with a deadline of: " + this.getEndTime());


    }

    @Override
    public void printTask() {
        if (this.isCompleted) {
            System.out.println(".[" + getTaskType(this) + "][X]" + this.taskName + " (by: " + this.getEndTime() + ")");
        } else {
            System.out.println(".[" + getTaskType(this) + "][ ]" + this.taskName + " (by: " + this.getEndTime() + ")");
        }

    }



    public void setAsDeadline(){
        this.taskType = "D";
    }
    @Override
    public String getTaskType (Task task){
        return this.taskType;
    }

}
