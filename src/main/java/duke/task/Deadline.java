package duke.task;

public class Deadline extends Task{

    public String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.taskChar = "[D]";
        this.by = by;
        print();
    }

    @Override
    public void print() {
        formattedTask = taskChar + status + " " + taskDescription + " (by: " + by + ")";
        System.out.println(formattedTask);
    }

}
