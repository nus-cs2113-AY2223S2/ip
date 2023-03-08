package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public void markDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" + this);
    }

    public void umarkDone() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n" + this);
    }

    public boolean findMatch(String partialName) {
        return description.contains(partialName);
    }
    protected String getDescription() {
        return description;
    }

    protected String status(){
        if(isDone) {
            return "X";
        }
        else {
            return " ";
        }
    }

    @Override
    public String toString() {
        return "[" + status() + "] " + getDescription();
    }
}
