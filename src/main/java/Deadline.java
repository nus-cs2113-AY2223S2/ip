import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Deadline extends Task{
    public String dueDate = new String();

    public Deadline(String taskName,int taskNumber,String by,String type){
        super(taskName,taskNumber);
        dueDate = by;
        description = "  [D]"+this.getDoneString()+" "+this.taskName+" (by: "+this.dueDate+")";
        this.type = type;
    }

    public static String readName(String input){
        //["deadline return book","by sunday"]
        String[] splitByForwardSlash = input.split("/");
        return (splitByForwardSlash[0].substring(9)).trim();
    }
    public static String readBy(String input){
        //["deadline return book","by Sunday"]
        String[] splitByForwardSlash = input.split("/");
        //["by","Sunday"]
        String[] splitBySpace = splitByForwardSlash[1].split(" ");
        return splitBySpace[1];
    }

    @Override
    public void updateTaskDescription(){
        this.description = "  [D]"+this.getDoneString()+" "+this.taskName+" (by: "+this.dueDate+")";
    }

    public String createEntry(){
//        try{
//            fw.append(Integer.toString(this.taskNumber)).append(".").append(this.type).append(".").append(this.getDoneString()).append(".").append(this.taskName).append(".").append(this.dueDate).append("\n");
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
        return Integer.toString(this.taskNumber)+"."+this.type+"."+this.getDoneString()+"."+this.taskName+"."+this.dueDate+"\n";
    }
}