public class Deadline extends Task {

    private String endTime;

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public Deadline(String description) {
        super(description);
    }

    @Override
    public void printTask(){
        if (this.isCompleted){
            System.out.println(".[D][X]" + this.taskName + " (by: " + this.getEndTime() + ")");
        }else {
            System.out.println(".[D][ ]" + this.taskName + " (by: " + this.getEndTime() + ")");
        }

    }

}
