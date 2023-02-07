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

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public void setTodo() {
        isTodo = true;
    }

    public void setDeadline() {
        isDeadline = true;
    }

    public void setEvent() {
        isEvent = true;
    }

    public String getType() {
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

    public String getBy() {
        return null;
    }

    public String getFrom() {
        return null;
    }

    public String getTo() {
        return null;
    }
}