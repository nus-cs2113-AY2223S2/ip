package keqing.tasks;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected static int taskCount = 0;
    protected boolean isDone = false;


    public void setDone() {
        isDone = true;
    }

    public void setUndone() {
        isDone = false;
    }

    public Task() {
    }

    public Task(String description) {
        this.description = description;
    }

    public String getTaskType() {
        return null;
    }

    public String getDescription() {
        return description;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public static void setTaskCount(int count) {
        taskCount = count;
    }

    public String toString() {
        if(isDone){
            return "[X] " +  description;
        }
        else{
            return "[ ] " +  description;
        }
    }

    public ArrayList<String> returnAttribute() {
        return null;
    }

    public boolean getStatus() {
        return isDone;
    }
}