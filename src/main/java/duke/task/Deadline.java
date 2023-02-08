package duke.task;

public class Deadline extends Task{

    public String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.taskChar = "[D]";
        this.by = by;
        System.out.print("Added: ");
        print();
    }

    @Override
    public void print() {
        System.out.println(taskChar + status + " " + taskDescription + " (by: " + by + ")");
    }

}
