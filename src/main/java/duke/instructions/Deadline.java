package duke.instructions;

public class Deadline extends Task{
    String input;
    boolean isComplete;
    String status;
    protected String taskType;
    public Deadline(String input) {
        super(input);
        this.input = input;
        this.taskType = "D";
    }


    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    @Override
    public String getTaskType(){
        return "D";
    }
    public void statusIcon(boolean isComplete){
        this.isComplete = isComplete;
        this.status = (this.isComplete ? "X" : " ");
    }

    /**
     * Format the input task
     * @return return the new format of the task
     */
    @Override
    public String getState(){
        if(input.contains("|")){
            String[] inputCommands = input.split("\\|");
            return "      [D]" + "[" + this.status + "]" + " "
                    + inputCommands[2].trim() + " (by: " + inputCommands[3].trim() + ")"
                    + System.lineSeparator();
        }else {
            String deadline = input.substring(input.indexOf('/') + 1);
            StringBuffer showDeadline = new StringBuffer(deadline);
            showDeadline.insert(2, ":");
            String arrayOfDeadline = this.description.substring(description.indexOf(" ") + 1);
            String[] eventName = arrayOfDeadline.split("/");
            return "      [D]" + "[" + getStatusIcon() + "]" + " "
                    + eventName[0] + " (" + showDeadline + ")"
                    + System.lineSeparator();
        }
    }

    /**
     * Format the task
     * @return return the formatting task and save it in text file
     */
    @Override
    public String getTaskList(){
        String deadline = input.substring(input.indexOf(' ') + 1);
        String[] newDeadline = deadline.split("/by");
        return newDeadline[0] + " | " + newDeadline[1];


    }

    @Override
    public String guideline(){
        return "     Got it. I've added this task: "
                + System.lineSeparator();
    }

    public String taskStatus(){
        if(getStatusIcon().equals("X")){
            return "Done";
        }else{
            return "Not Done";
        }
    }
}
