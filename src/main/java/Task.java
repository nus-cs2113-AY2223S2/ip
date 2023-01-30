public class Task {
    protected String description;
    protected boolean isDone;
    private static int numberOfTasks = 0;

    public int getNumberOfTasks() {
        return numberOfTasks;
    }


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }



    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

//    public String getDescription() {
//        return this.description;
//    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
