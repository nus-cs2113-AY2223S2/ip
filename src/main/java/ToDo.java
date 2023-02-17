public class ToDo extends Task{
    public ToDo(String taskName,int number,String type){
        super(taskName,number);
        this.description ="  [T]"+this.getDoneString()+" "+this.taskName;
        this.type=type;
    }

    @Override
    public void updateTaskDescription(){
        this.description = "  [T]"+this.getDoneString()+" "+this.taskName;
    }


    public static void loadFromFileToTaskArray(String input){
        String[] splitByFullStop = input.split(".");
        int taskNumber=Integer.parseInt(splitByFullStop[0]);
        String taskName = splitByFullStop[3];
        String taskType = splitByFullStop[1];

        ToDo todo=new ToDo(taskName,taskNumber,taskType);
        todo.isDone= splitByFullStop[2].equals("X");

        Task.addToTaskArrayList(todo);
    }
}