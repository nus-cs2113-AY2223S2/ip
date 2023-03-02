package tasks;

public class Task {

    public String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }
    
    public String printTask(){
        return description;
    }

    public String encode(){
        String markStatus = "0";
        if (this.isDone){
            markStatus = "1";
    }
        String encodedString = markStatus + "/" + description;
        return encodedString;
    }
}
