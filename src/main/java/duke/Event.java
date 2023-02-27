package duke;

public class Event extends Task {
    protected String startTime;
    protected String finishTime;

    public Event(String name, boolean isDone, int taskId, String startTime, String finishTime) {
        super(name, isDone, taskId);
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public String toString() {
        if (this.getIsDone() == true) {
            return " [E][X]" + this.getName() + " (from: " + this.startTime
                    + " to: " + this.finishTime + ")";
        }
        return " [E][ ]" + this.getName() + " (from: " + this.startTime
                + " to: " + this.finishTime + ")";
    }

    public void print() {
        if (this.isIsDone() == false) {
            System.out.println((this.getTaskId() + 1) + "." + this.toString());
        } else {
            System.out.println((this.getTaskId() + 1) + "." + this.toString());
        }
    }
}
