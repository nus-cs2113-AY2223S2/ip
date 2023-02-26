public class Deadline extends Tasklist {
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
        return (splitByForwardSlash[1]).substring(3);
    }

    @Override
    public void updateTaskDescription(){
        this.description = "  [D]"+this.getDoneString()+" "+this.taskName+" (by: "+this.dueDate+")";
    }

    public String createEntry(){
        return Integer.toString(this.taskNumber)+"."+this.type+"."+this.getDoneString()+"."+this.taskName+"."+this.dueDate+"\n";
    }
}