public class ToDo extends Tasklist{
    public ToDo(String taskName,int number,String type){
        super(taskName,number);
        this.description ="  [T]"+this.getDoneString()+" "+this.taskName;
        this.type=type;
    }

    @Override
    public void updateTaskDescription(){
        this.description = "  [T]"+this.getDoneString()+" "+this.taskName;
    }
}