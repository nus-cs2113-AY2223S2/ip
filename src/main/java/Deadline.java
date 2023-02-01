public class Deadline extends Task{
    private String doBy;
    Deadline(String name, String doBy){
        super(name, TaskType.DEADLINE);
        this.doBy = doBy;
    }

    @Override
    public String toString(){
        return super.getTaskPrefixWithName() + " (by: " + doBy + ")";
    }
}
