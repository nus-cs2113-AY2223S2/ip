public class Task
{

    private String taskName;

    protected TaskType taskType;

    private boolean isDone;

    public Task (String taskName)
    {
        this (taskName, false);
    }
    
    public Task (String taskName, boolean isDone)
    {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String getTaskName ()
    {
        return taskName;
    }

    public String getStatusIcon () {
        return (isDone ? "X" : " ");
    }

    public String getTaskType () {
        if (taskType == null) { //need to figure this out later
            return " ";
        }
        switch (taskType) {
            case TO_DO:
                return "T";
            case DEAD_LINE:
                return "D";
            case EVENT:
                return "E";
            default:
                return " "; //technically unreachable
        }
    }

    public void setDone ()
    {
        isDone = true;
    }

    public void setNotDone ()
    {
        isDone = false;
    }

    public String getTaskStatus ()
    {
        return "[" + getTaskType () + "][" + getStatusIcon () + "]" + getTaskName ();
    }

}
