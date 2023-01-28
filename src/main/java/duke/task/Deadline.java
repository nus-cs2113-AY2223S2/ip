package duke.task;

public class Deadline extends ToDo {

    protected String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        super.type = "[D]";
        this.deadline = deadline;
    }

    public String getDeadline(){
        return this.deadline;
    }


    @Override
    public String toString(){
        return  checkBoxOutput() +this.getTaskName() + " (by: " + this.getDeadline() + ")";
    }
}
