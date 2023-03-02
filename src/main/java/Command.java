import java.time.LocalDate;

abstract public class Command {
    protected String type = " ";
    protected String content = " ";
    protected LocalDate by;
    protected LocalDate from;
    protected LocalDate to;
    protected int targetTaskIndex = -1;

    public Command(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public String getContent(){
        return content;
    }

    public LocalDate getBy(){
        return by;
    }

    public LocalDate getFrom(){
        return from;
    }

    public LocalDate getTo(){
        return to;
    }

    public int getTargetTaskIndex(){
        return targetTaskIndex;
    }
}