public class Task {
    private String description;
    private boolean isDone;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String description) {
        setDescription(description);
        setDone(false);
    }

    private String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public String getTaskString() {
        return '[' + this.getClass().getSimpleName().substring(0, 1) + "][" + getStatusIcon() + "] " + getDescription();
    }
}

