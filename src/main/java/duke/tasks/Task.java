package duke.tasks;

public class Task {
    protected String taskName;
    protected static int indexCount = 0;
    protected boolean isDone;
    protected String type;

    /**
     * Constructor for Task class.
     *
     * @param taskName Task description.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        indexCount++;
    }

    /**
     * Setters for isDone.
     *
     * @param done true if task is done, false if task is not done.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Gettera for isDone.
     *
     * @return The current "done" status of the task
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Getters for indexCount.
     *
     * @return Current index count of a
     */
    public static int getIndexCount() {
        return indexCount;
    }

    /**
     * Decrements indexCount by 1
     */
    public static void decrementIndexCount() {
        indexCount--;
    }

    /**
     * Getters for Task type
     *
     * @return a String representing the type of the task
     */
    public String getType() {
        return type;
    }

    /**
     * Getters for taskName.
     *
     * @return Task description.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Outputs a checkmark if task is done, and a blank space if task is not done.
     *
     * @return a string with a checkmark if task is done, and a blank space if task is not done.
     */
    public String checkBoxOutput() {
        String checkMark = isDone ? " [X] " : " [ ] ";
        String taskType = type;

        return taskType + checkMark;
    }

    /**
     * Outputs a message when task is added
     */
    public void printAdded() {
        int taskCount = indexCount;
        String word1;
        String word2;
        if (taskCount == 1) {
            word1 = "is ";
            word2 = " task";
        } else {
            word1 = "are ";
            word2 = " tasks";
        }
        System.out.println("Got it. I've added this task:\n " + this);
        System.out.println("Now there " + word1 + indexCount + word2 + " in your list");
    }

    /**
     * Outputs a message when task is deleted
     */
    public void printDeleted() {
        System.out.println("Got it. I've deleted this task:\n " + this);
    }
}
