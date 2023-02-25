package app.tasks;

public class Deadline extends Task{
    protected String deadline;
    public Deadline(String taskDescription, boolean isDone, String deadline) {
        super(taskDescription, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString(){
        return " [D][" + getStatusIcon() + "] " + taskDescription + " (by: " + deadline + ")";
    }

}
