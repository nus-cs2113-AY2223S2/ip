package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol = "[T]";

    public String getDescription() {
        return description;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getSymbol() {
        return symbol;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsUnDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString(){
        return "[T]" + this.getStatusIcon() + " " + getDescription();
    }

    public String toFile() {
        return this.getStatusIcon() + " : " +"T"+ " : " + this.description;
    }
}


