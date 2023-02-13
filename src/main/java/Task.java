import java.util.ArrayList;

public class Task {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static int numOfTasks = 0;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTaskType() {
        return "";
    }

    public String getBy() {
        return "";
    }
    public String getFrom() {
        return "";
    }

    public String getTo() {
        return "";
    }

    @Override
    public String toString() {
        String icon = getStatusIcon();
        String output;
        output = "[" + icon + "] " + description;
        return output;
    }

}
