public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? StrIntLib.done : StrIntLib.notDone); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getIcon() {
        return " ";
    }

    public String getStart() {
        return "";
    }
    public String getEnd() {
        return "";
    }
    public String getDeadline() {
        return "";
    }
}

