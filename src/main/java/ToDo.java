public class ToDo extends Task{
    public ToDo(String taskName,int number){
        super(taskName,number);
        this.description ="  [T]"+this.getDoneString()+" "+this.taskName;
    }

    @Override
    public void updateTaskDescription(){
        this.description = "  [T]"+this.getDoneString()+" "+this.taskName;
    }


    public static void loadFromFileToTaskArray(String input){
        String[] splitByOpenSquareBrackets = input.split("\\[");
        int taskNumber=Integer.parseInt(splitByOpenSquareBrackets[0].substring(0,1));
        String taskName = splitByOpenSquareBrackets[2].substring(3);

        ToDo todo=new ToDo(taskName,taskNumber);
        todo.isDone=(splitByOpenSquareBrackets[2].substring(0).equals("X"))?true:false;

        Task.addToTaskArrayList(todo);
    }
}