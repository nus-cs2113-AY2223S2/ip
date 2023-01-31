public class Deadline extends Todo {
    private String deadline;

    public Deadline(String[] args) {
        super(args[0]);
        this.taskType = "D";
        this.deadline = args[1];
    }
}
