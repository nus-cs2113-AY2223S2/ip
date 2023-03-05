package duke.instructions;

public class Task {

    protected String description;
    protected boolean isDone = false;
    protected String state;
    protected String checkComplete;
    protected static String taskType;


    protected String taskList;
    public Task(String description) {
        this.description = description;
    }
    public Task(){

    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        checkComplete = (this.isDone ? "X" : " ");
        return checkComplete; // mark done task with X
    }
    public String getState(){
        return state;
    }
    public String getTaskList(){
        return taskList;
    }
    public  String getTaskType(){
        return taskType;
    }


    public String guideline(){
       return "     Here are the task in your list: "
               + System.lineSeparator();
    }
    public String numberOfTask(int length){
        return "     Now you have "
                + length + " tasks in the list.";

    }

    public String taskStatus(){
        if(getStatusIcon().equals("X")){
            return "Done";
        }else{
            return "Not Done";
        }
    }
}

