package task;

public abstract class Task {
    private String description;
    private char type;
    private boolean isDone;

    public Task(String description, char type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getType() {
        return this.type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public abstract String getListDescription();
}
