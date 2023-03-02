public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return this.description;
    }

    public String getTaskIcon() {
        return "";
    }

    public String getDeadlineBy() {
        return "";
    }

    public String getEventStart() {
        return "";
    }

    public String getEventEnd() {
        return "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusNum() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "]" + description;
    }
}
