package duke.instructions;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
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



