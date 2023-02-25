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
    protected String type = new String();

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

    public static Task get(int index){
        return taskArray[index];
    }

    public static void printList(){
        System.out.println(DASH+"\nHere are the tasks in your list:");
        for(int index = 0;index<Task.lastIndex;index++){
            System.out.println(Integer.toString(index+1)+"."+(taskArray[index].description).trim());
        }
        System.out.println(DASH + "\n");
    }

    //file I/O
    public String createEntry(){
        return Integer.toString(this.taskNumber)+(".")+this.type+"."+this.getDoneString()+"."+(this.taskName)+"\n";
    }

    //format of file = number.type.done.name.from.to
    public static void loadFileContentsToTaskArray(File f) throws FileNotFoundException{
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] taskDetails = (s.nextLine()).split("\\.");
            if(taskDetails[1].equals("T")){
                ToDo todo= new ToDo(taskDetails[3],Integer.parseInt(taskDetails[0]),taskDetails[1]);
                todo.isDone = taskDetails[2].equals("[X]");
                todo.updateTaskDescription();
                Task.addToTaskArrayList(todo);
            }
            else if(taskDetails[1].equals("E")){
                Event event = new Event(taskDetails[3],Integer.parseInt(taskDetails[0]),taskDetails[4],taskDetails[5],taskDetails[1]);
                event.isDone = taskDetails[2].equals("[X]");
                event.updateTaskDescription();
                Task.addToTaskArrayList(event);
            }
            else if(taskDetails[1].equals("D")){
                Deadline deadline = new Deadline(taskDetails[3],Integer.parseInt(taskDetails[0]),taskDetails[4],taskDetails[1]);
                deadline.isDone = taskDetails[2].equals("[X]");
                deadline.updateTaskDescription();
                Task.addToTaskArrayList(deadline);
            }
        }
    }

    public static void save(File f){
        try{
            if(!f.exists()){
                f.createNewFile();
            }
            if(Task.lastIndex>0){     //possible to use continue here instead?
                FileWriter fw = new FileWriter(f);
                for(int index=0;index<Task.lastIndex;index++){
                    fw.append((Task.get(index)).createEntry());
                }
                fw.close();
            }
            if(Task.lastIndex==0){
                FileWriter fw = new FileWriter(f);
                fw.close();
            }
            System.out.println(DASH+"\nSaved!\n"+DASH);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void deleteFromTaskArray(int indexToDelete){
        System.out.println(DASH+"\n Bye Bye task! It was nice meeting you :)\n"+taskArray[indexToDelete-1].description);
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

        System.out.println(" Now you have "+Integer.toString(lastIndex)+" tasks in the list.\n"+DASH);
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