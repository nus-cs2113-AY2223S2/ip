public class ToDo extends Task{
    public ToDo(String taskName,int number){
        super(taskName,number);
        this.description ="  [T]"+this.getDoneString()+" "+this.taskName;
    }

    @Override
    public void updateTaskDescription(){
        this.description = "  [T]"+this.getDoneString()+" "+this.taskName;
    }
}