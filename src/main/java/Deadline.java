public class Deadline extends ToDo {

    protected String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        super.type = Types.DEADLINE;
        this.deadline = deadline;
    }

    public String getDeadline(){
        return this.deadline;
    }


    @Override
    public String listDescription(){
        return  checkBoxOutput() +this.getTaskName() + " (by: " + this.getDeadline() + ")";
    }
}
