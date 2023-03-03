import java.time.LocalDate;

/**
 * Represents an abstract format of a command.
 * A <code>Command</code> object has null type, content, by, from, to, targetTaskIndex, and targetWord.
 * Classes that extend this class should fill in necessary information through their constructors or methods.
 */

abstract public class Command {
    protected String type = " ";
    protected String content = " ";
    protected LocalDate by;
    protected LocalDate from;
    protected LocalDate to;
    protected int targetTaskIndex = -1;
    protected String targetWord = " ";

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

    public String getTargetWord(){
        return targetWord;
    }
}