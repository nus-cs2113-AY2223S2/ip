package duke.task;

public abstract class Task {
    private static int numTask;
    private String desciption;
    private boolean isDone;

    public Task(String desciption) {
        this.desciption = desciption;
        this.isDone = false;
        numTask++;
    }

    public static int getNumTask() {
        return numTask;
    }

    public static void setNumTask(int number) {
        numTask = number;
    }

    public String getTaskDescription() {
        return desciption;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getTaskDescription();
    }

    public abstract String getTaskType();
}
