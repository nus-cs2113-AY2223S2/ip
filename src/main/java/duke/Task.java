package duke;

public abstract class Task {
    protected String description;


    public Task(String description) {
        this.description = description;

    }
    public String getStatusIcon() {
        return "";
    }
    public abstract String getTypeIcon();
    public abstract void setDone(boolean b);
    public String getDescription () {
        return this.description;
    }
}
