public class Task {
    protected String description;
    protected boolean isDone;
    private static String[] listOfTasks = new String[100];

    public Task(){
        description = null;
        isDone = false;
    }
    public Task (String description){
        this.description = description;
        this.isDone = false;
    }

    public String getTaskName(){
        return description;
    }
    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }
}