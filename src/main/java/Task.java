public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        if (isDone) {
            return "[X] " + description;
        }
        return "[ ] " + description;
    }

    public void setDone(String mark) {
        if (mark.equals("mark")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    public String addTaskMessage() {
        return "Got it. I've added this task:\n  " + this.toString() + System.lineSeparator();
    }


}
