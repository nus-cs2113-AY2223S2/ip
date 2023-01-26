public class Task {

    private String taskName;
    private boolean isDone;


    public Task (String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setDone(){
        isDone = true;
    }

    public void setNotDone(){
        isDone = false;
    }

    public String getTaskStatus(){
        String taskStatus = "[";
        if (isDone) {
            taskStatus += "X";
        }
        else{
            taskStatus += " ";
        }
        taskStatus += ("] " + taskName);
        return  taskStatus;

    }

}
