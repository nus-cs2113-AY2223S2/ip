package grandduke.task;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String taskDesc, String deadline) {
        super(taskDesc);
        this.deadline = deadline;
    }

    @Override
    public String getTaskPrint() {
        return "[D]" + super.getTaskPrint() + " (by: " + deadline + ")";
    }

}
