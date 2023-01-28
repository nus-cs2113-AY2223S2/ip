public class Task
{

    private String taskName;
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
        return "[" + getStatusIcon () + "]" + getTaskName ();
    }

}
