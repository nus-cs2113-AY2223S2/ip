public class Events extends Task{
    private String timeLine;
    private String taskLabel = "[E]";
    public Events (String input){
        super(input.substring(6,input.indexOf('/') - 1));
        super.setTaskLabel(taskLabel);
        timeLine = "(" + getStartTime(input) + getEndTime(input) + ")";
    }
    public String[] splitInput(String input){
        String[] inputAfterSplit = input.split("/", 3); // split twice to generate three strings
        return inputAfterSplit;
    }
    @Override
    public String getStartTime(String input){
        String[] inputAfterSplit = splitInput(input);
        String startTime = inputAfterSplit[1];
        StringBuffer startTimeCorrectFormat = new StringBuffer(startTime);
        startTimeCorrectFormat.insert(4,":");
        return startTimeCorrectFormat.toString();
    }
    @Override
    public String getEndTime(String input){
        String[] inputAfterSplit = splitInput(input);
        String startTime = inputAfterSplit[2];
        StringBuffer endTimeCorrectFormat = new StringBuffer(startTime); // convert to StringBuffer for inserting ':'
        endTimeCorrectFormat.insert(2,":");
        return endTimeCorrectFormat.toString();
    }
    @Override
    public String toString(){
        return this.taskLabel + this.mark + " " + this.description + " " + this.timeLine;
    }
}
