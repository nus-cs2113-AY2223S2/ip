package orca;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task() {
        this.isDone = false;
    }


    /**
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * @param isDone
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
