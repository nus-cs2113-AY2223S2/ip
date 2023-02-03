import java.util.ArrayList;
import java.util.Arrays;

public class Task {
    //class level attributes
    private static Task[] taskArray = new Task[100];
    public static int lastIndex = 0;
    public static String DASH = "__________________________________";

    //instance level attributes
    protected boolean isDone=false;
    protected String taskName = new String();
    protected int taskNumber;
    protected String description = new String();

    public Task(String taskName,int taskNumber){
        this.taskName = taskName;
        this.taskNumber =taskNumber;
    }

    public static void addToTaskArrayList(Task taskToBeAdded){
        taskArray[lastIndex]=taskToBeAdded;
        lastIndex++;
    }

    public static void markOrUnmark(int taskIndex){
        taskArray[taskIndex-1].isDone = (!taskArray[taskIndex - 1].isDone);
        taskArray[taskIndex-1].updateTaskDescription();
        System.out.println(DASH+"\nNice! I've marked this task as done:\n"+taskArray[taskIndex-1].description+"\n"+DASH);
    }

    public static void printList(){
        System.out.println(DASH+"\nHere are the tasks in your list:");
        for(int index = 0;index<Task.lastIndex;index++){
            System.out.println(Integer.toString(index+1)+"."+taskArray[index].description);
        }
        System.out.println(DASH + "\n");
    }

    public void acknowledgeTaskAdded(){
        String totalNumberOfTasks=Integer.toString(Task.lastIndex);
        System.out.println(DASH+"\nGot it. I've added this task:\n"+this.description+"\nNow you have "+
                totalNumberOfTasks+" tasks in the list.\n"+DASH);

    }

    public String getDoneString(){
        return this.isDone?"[X]":"[ ]";
    }

    //to be overridden by subclasses
    public void updateTaskDescription(){

    }
}
//test cases
//todo SHawn: Answer CS2113 Tutorial Questions

//deadline weekly CS2113 quiz /by Monday 9pm

//list

//event CS2113 lecture /from Friday 4pm /to 6pm

//mark 1

//list
