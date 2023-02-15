import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Task{
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
            System.out.println(Integer.toString(index+1)+"."+(taskArray[index].description).trim());
        }
        System.out.println(DASH + "\n");
    }
    //file I/O
    public static void save(){
        File f = new File("data.txt");
        try{
            if(!f.exists()){

                f.createNewFile();
//                System.out.println("file created "+f.getCanonicalPath()); //returns the path string
            }
            FileWriter fw = new FileWriter(f);
            fw.write(Integer.toString(taskArray[0].taskNumber)+"."+(taskArray[0].description).trim());
            if(taskArray.length>1){
                for(int index=1;index<lastIndex;index++){
                    fw.append("\n"+Integer.toString(taskArray[index].taskNumber)+"."+(taskArray[index].description).trim());
                }
            }
            fw.close();
            System.out.println(DASH+"\nSaved!\n"+DASH);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void loadFileContentsToTaskArray(File f) throws FileNotFoundException{
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            ToDo.loadFromFileToTaskArray(s.nextLine());
        }
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