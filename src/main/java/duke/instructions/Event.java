
package duke.instructions;

public class Event extends Task{
    protected  String input;
    protected String taskType;
    protected String taskName;

    public Event(String input) {
        super(input);
        this.input = input;
        this.taskType = "E";
        this.taskName = input;
    }

    /**
     * format the task
     * @return return the respective task format based on the input
     * if the input from text file, then it will execute the if condition, if the input from the input, then it will execute the else condition
     */

    @Override
    public String getState(){

        if(description.contains("|")){
            String[] inputCommands = description.split("\\|");
            String[] event = inputCommands[3].split("-");
            return "      [E]" + "[" + getStatusIcon() + "]" + " "
                    + inputCommands[2].trim() +
                    " (from: " + event[0].trim() + " to: "
                    + event[1].trim() + ")" + System.lineSeparator();
        }else {
            String wholeArrayOfEvent = this.description.substring(description.indexOf(" ") + 1);
            String[] arrayOfEvent = wholeArrayOfEvent.split("/from");
            String eventStart = arrayOfEvent[1].split("/to")[0].trim();
            String eventEnd = arrayOfEvent[1].split("/to")[1].trim();

            String full;
            full = "      [E]" + "[" + getStatusIcon() + "]" + " "
                    + arrayOfEvent[0] +
                    "(from: " + eventStart + " to: "
                    + eventEnd + ")" + System.lineSeparator();

            return full;
        }
}

@Override
    public String guideline(){
        return "     Got it. I've added this task: "
                + System.lineSeparator();
    }


    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String getTaskType(){
        return this.taskType;
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
