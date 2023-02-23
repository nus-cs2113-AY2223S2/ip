package duke.task;

/**
 * Task type that includes a by time
 */
public class Deadline extends Task{
    private String doBy;
    public Deadline(String name, String doBy){
        super(name, TaskType.DEADLINE);
        this.doBy = doBy;
    }

    public String getDoBy(){
        return doBy;
    }

    @Override
    public String toString(){
        return super.getTaskPrefixWithName() + " (by: " + doBy + ")";
    }
}
