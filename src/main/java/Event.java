import com.sun.source.tree.WhileLoopTree;

public class Event extends Task{
    String input;
    public Event(String input) {
        super(input);
        this.input = input;
    }
    @Override
    public String getState(){
        String wholeArrayOfEvent = this.description.substring(description.indexOf(" ") + 1);
        String[] arrayOfEvent = wholeArrayOfEvent.split("/from");
        String eventStart = arrayOfEvent[1].split("/to")[0].trim();
        String eventEnd = arrayOfEvent[1].split("/to")[1].trim();

        String full;
        full = "      [E]" + "[" + getStatusIcon() + "]" +  " " + arrayOfEvent[0] +
                "(from: " + eventStart + " to: " + eventEnd + ")" + System.lineSeparator();

        return full;
}

@Override
    public String guideline(){
        return "     Got it. I've added this task: " + System.lineSeparator();
    }
}
