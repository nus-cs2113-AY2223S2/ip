public class Deadline extends Todo {
    private String deadline;

    public Deadline(String[] args) {
        super(args[0]);
        this.taskType = "D";
        this.deadline = args[1];
    }

    public String getDeadline() {
        return deadline;
    }

    public String printTask(){
        return this.getTaskIcon() + this.getStatusIcon() + ' ' + this.description + " (by: " + deadline + ")";
    }
}
