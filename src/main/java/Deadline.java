import java.util.Arrays;

public class Deadline extends Task{
    public String dueDate = new String();

    public Deadline(String taskName,int taskNumber,String by){
        super(taskName,taskNumber);
        dueDate = by;
        description = "  [D]"+this.getDoneString()+" "+this.taskName+" (by: "+this.dueDate+")";

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
}