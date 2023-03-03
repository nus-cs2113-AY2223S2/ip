package duke.task;

/**
 * The parent class of all the tasks
 * Initialises and stores name and completion status of the task
 */
public class Task {
    private boolean isDone;
    private String name;
    private TaskType taskType;

    Task(String name, TaskType taskType) {
        this.name = name;
        this.taskType = taskType;
        isDone = false;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public boolean isDone(){
        return isDone;
    }

    public TaskType getTaskType(){
        return taskType;
    }

    public String getTaskName(){
        return name;
    }
    public String getTaskPrefixWithName(){
        String isDoneCheck;
        if(isDone){
            isDoneCheck = "X";
        } else{
            isDoneCheck = " ";
        }
        String taskTypeChar = null;
        switch(taskType){
        case TODO:
            taskTypeChar = "T";
            break;
        case DEADLINE:
            taskTypeChar = "D";
            break;
        case EVENT:
            taskTypeChar = "E";
            break;
        }
        return "[" + taskTypeChar + "][" + isDoneCheck + "] " + name;
    }
}
