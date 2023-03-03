package bro.tasks;

public class Deadline extends Task{
    private String deadline;

    public String getDeadline() {
        return deadline;
    }

    public Deadline(String name, String deadline) {
        super(name);
        this.setType("D");
        this.deadline = deadline;
    }
    public Deadline(String name, boolean completed, String deadline) {
        super(name, completed);
        this.setType("D");
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}