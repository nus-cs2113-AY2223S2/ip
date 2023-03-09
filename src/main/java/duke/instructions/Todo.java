package duke.instructions;

public class Todo extends Task{

    protected String taskType;
    public Todo(String description) {
        super(description);
        this.taskType = description;
    }

    @Override
    public String getTaskName(){
        return this.taskName;
    }

    @Override
    public String getState(){
        String arrayOfTodo = this.description.substring(description.indexOf(" ") + 1);
        return "      [T]" + "[" + getStatusIcon() + "]" +  " "
                + arrayOfTodo + System.lineSeparator();
    }

    @Override
    public String guideline(){
        return "     Got it. I've added this task: "
                + System.lineSeparator();
    }

    /**
     * get the task type
     * @return the  type of task
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }


   @Override
    public String getTaskList(){
       String arrayOfTodo = this.description.substring(description.indexOf(" ") + 1);
        return arrayOfTodo;
   }

}



