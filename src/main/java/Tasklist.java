import java.util.ArrayList;
public class Tasklist {
    //class level attributes
    private static Tasklist[] taskArray = new Tasklist[100];
    public static int lastIndex = 0;
    public static String DASH = "__________________________________";

    //instance level attributes
    protected boolean isDone=false;
    protected String taskName = new String();
    protected int taskNumber;
    protected String description = new String();
    protected String type = new String();

    public Tasklist(String taskName,int taskNumber){
        this.taskName = taskName;
        this.taskNumber =taskNumber;
    }

    public static void addToTaskArrayList(Tasklist taskToBeAdded){
        taskArray[lastIndex]=taskToBeAdded;
        lastIndex++;
    }

    public static void markOrUnmark(int taskIndex){
        taskArray[taskIndex-1].isDone = (!taskArray[taskIndex - 1].isDone);
        taskArray[taskIndex-1].updateTaskDescription();
        Storage.save();
        UserInterface.markMessage(taskIndex);
    }

    public static Tasklist get(int index) {
        return taskArray[index];
    }

    public static ArrayList<String> find(String queryString){
        ArrayList<String> foundTaskList = new ArrayList<String>();
        for(int index=0;index<lastIndex;index++){
            if(taskArray[index].taskName.contains(queryString)){
                foundTaskList.add(taskArray[index].description);
            }
        }
        return foundTaskList;
    }

    //file I/O
    public String createEntry(){
        return Integer.toString(this.taskNumber)+(".")+this.type+"."+this.getDoneString()+"."+(this.taskName)+"\n";
    }


    public static void deleteFromTaskArray(int indexToDelete){

        if(lastIndex>1){
            for(int index = indexToDelete-1;index<lastIndex-1;index++){
                taskArray[index] = taskArray[index+1];
                taskArray[index].taskNumber -=1;
            }
        }
        if(lastIndex==1){
            taskArray[0]=null;
        }

        lastIndex--;
        taskArray[lastIndex]=null;
        UserInterface.deleteMessage(indexToDelete);
    }

    public String getDoneString(){
        return this.isDone?"[X]":"[ ]";
    }

    //to be overridden by subclasses
    public void updateTaskDescription(){
    }

}
