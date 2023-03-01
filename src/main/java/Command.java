abstract public class Command {
    protected String type = " ";
    protected String content = " ";
    protected String by = " ";
    protected String from = " ";
    protected String to = " ";
    protected int targetTaskIndex = -1;

    public Command(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public String getContent(){
        content = content.replace("| ", "");
        content = content.replace("by ", "");
        content = content.replace("from ", "");
        content = content.replace("to ", "");
        return content;
    }

    public String getBy(){
        return by;
    }

    public String getFrom(){
        return from;
    }

    public String getTo(){
        return to;
    }

    public int getTargetTaskIndex(){
        return targetTaskIndex;
    }
}