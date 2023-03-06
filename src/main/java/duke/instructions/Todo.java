package duke.instructions;

public class Todo extends Task{

    protected String taskType;
    boolean isComplete;
    String status;
    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }


    public void statusIcon(boolean isComplete){
        this.isComplete = isComplete;
        this.status = (this.isComplete ? "X" : " ");
    }

    @Override
    public String getState(){
        if(description.contains("|")){
            String[] inputCommands = description.split("\\|");
            return "      [T]" + "[" + getStatusIcon() + "]" + " "
                    + inputCommands[2].trim() + System.lineSeparator();
        }else {
            String arrayOfTodo = this.description.substring(description.indexOf(" ") + 1);
            return "      [T]" + "[" + getStatusIcon() + "]" + " "
                    + arrayOfTodo + System.lineSeparator();
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
       String arrayOfTodo = this.description.substring(description.indexOf(" ") + 1);
        return arrayOfTodo;
   }
    public String taskStatus(){
        if(getStatusIcon().equals("X")){
            return "Done";
        }else{
            return "Not Done";
        }
    }
    }





