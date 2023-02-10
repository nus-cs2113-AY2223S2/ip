package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isTodo = false;
    protected boolean isDeadline = false;
    protected boolean isEvent = false;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public boolean isTodo() {
        return isTodo;
    }
    public boolean isDeadline() {
        return isDeadline;
    }
    public boolean isEvent() {
        return isEvent;
    }

    public String getTypeIcon() {
        if (isTodo) {
            return "T";
        } else if (isDeadline) {
            return "D";
        } else if (isEvent) {
            return "E";
        }
        return " ";
    }

    public String getDoneIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setTodo() {
        isTodo = true;
    }

    public void setDeadline() {
        isDeadline = true;
    }
    public String getBy() {
        return null;
    }

    public void setEvent() {
        isEvent = true;
    }
    public String getFrom() {
        return null;
    }
    public String getTo() {
        return null;
    }
}