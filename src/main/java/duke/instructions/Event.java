
package duke.instructions;

public class Event extends Task{
    protected  String input;
    protected String taskType;

    public Event(String input) {
        super(input);
        this.input = input;
        this.taskType = "E";
    }

    @Override
    public String getState(){
        String wholeArrayOfEvent = this.description.substring(description.indexOf(" ") + 1);
        String[] arrayOfEvent = wholeArrayOfEvent.split("/from");
        String eventStart = arrayOfEvent[1].split("/to")[0].trim();
        String eventEnd = arrayOfEvent[1].split("/to")[1].trim();

        String full;
        full = "      [E]" + "[" + getStatusIcon() + "]" +  " "
                + arrayOfEvent[0] +
                "(from: " + eventStart + " to: "
                + eventEnd + ")" + System.lineSeparator();

        return full;
}

@Override
    public String guideline(){
        return "     Got it. I've added this task: "
                + System.lineSeparator();
    }


    @Override
    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String getTaskList(){

        String wholeArrayOfEvent = this.description.substring(description.indexOf(" ") + 1);
        String[] arrayOfEvent = wholeArrayOfEvent.split("/from");
        String eventStart = arrayOfEvent[1].split("/to")[0].trim();
        String eventEnd = arrayOfEvent[1].split("/to")[1].trim();

        return arrayOfEvent[0] +
                " | " + eventStart + "-"
                + eventEnd + System.lineSeparator();
    }
}
