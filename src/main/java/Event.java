public class Event extends Tasklist{
    private String from=new String();
    private String to=new String();
    public Event(String taskName,int taskNumber,String from,String to,String type){
        super(taskName,taskNumber);
        this.from=from;
        this.to=to;
        description = "  [E]"+this.getDoneString()+" "+this.taskName+" (from: "+this.from +" to: "+this.to+")";
        this.type = type;

    }

    public static String readName(String input){
        //["event project meeting","from Mon 2pm","to 4pm"]
        String[] splitByForwardSlash = input.split("/");
        return (splitByForwardSlash[0].substring(6)).trim();
    }

    public static String[] readFromTo(String input){
        //["event project meeting","from Mon 2pm ","to 4pm"]
        String[] splitByForwardSlash = input.split("/");
        //"Mon 2pm"
        String fromString = splitByForwardSlash[1].substring(5).trim();
        //"4pm"
        String toString = splitByForwardSlash[2].substring(3).trim();

        String[] fromToArray = new String[2];
        fromToArray[0]=fromString;
        fromToArray[1]=toString;
        return fromToArray;
    }

    @Override
    public void updateTaskDescription(){
        this.description = "  [E]"+this.getDoneString()+" "+this.taskName+" (from: "+this.from +" to: "+this.to+")";
    }

    public String createEntry(){
        return Integer.toString(this.taskNumber)+"."+this.type+"."+this.getDoneString()+
                "."+ this.taskName+"."+this.from+"."+this.to+"\n";
    }
}
