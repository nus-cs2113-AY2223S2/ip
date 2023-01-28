public class Deadline extends Task {

    private String endTime;

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Deadline(String description) {
        super(description);
    }

    public void newDeadlineResponse() {
        System.out.println("Got it. I've added \"" + this.taskName +"\"");
        System.out.println("with a deadline of: " + this.getEndTime());


    }

    @Override
    public void printTask() {
        if (this.isCompleted) {
            System.out.println(".[D][X]" + this.taskName + " (by: " + this.getEndTime() + ")");
        } else {
            System.out.println(".[D][ ]" + this.taskName + " (by: " + this.getEndTime() + ")");
        }

    }

}
