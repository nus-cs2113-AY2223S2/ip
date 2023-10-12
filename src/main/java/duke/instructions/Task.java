package duke.instructions;

public class Task {

    protected String description;
    protected boolean isDone = false;
    protected String state;
    protected String checkComplete;
    protected static String taskType;

    boolean isComplete;
    String status;


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

    /**
     * To update the status of the task and use "X" to indicate the task had been completed
     * while " " is used to indicate the task haven't been done yet
     * @return return the completion of the task
     */
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



    /**
     * update the length of the task list
     * @param length the length of the current task list
     * @return return a string which shows the lasted number of tasks in the list
     */
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

