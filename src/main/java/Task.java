public class Task {
    private boolean isDone;
    private String name;
    private TaskType taskType;

    public static int totalTasks = 0;
    Task(String name, TaskType taskType) {
        this.name = name;
        this.taskType = taskType;
        isDone = false;
        ++totalTasks;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public boolean isDone(){
        return isDone;
    }

    public String getTaskPrefixWithName(){
        String isDoneCheck;
        if(isDone){
            isDoneCheck = "X";
        }
        else{
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
