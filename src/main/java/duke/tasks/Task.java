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
     * Getters for indexCount.
     *
     * @return Current index count of a
     */
    public static int getIndexCount(){
       return indexCount;
    }

    /**
     * Getters for Task type
     * @return a String representing the type of the task
     */
    public String getType(){
        return this.type;
    }

    @Override
    public String toString(){
        return "";
    }

    /**
     * Outputs a checkmark if task is done, and a blank space if task is not done.
     *
     * @return a string with a checkmark if task is done, and a blank space if task is not done.
     */
    public String checkBoxOutput(){
        String checkMark = isDone ? " [X] " : " [ ] ";
        String taskType = type;

        return  taskType + checkMark;
    }

    /**
     * Outputs a message when task is added
     */
    public void printAdded(){
        int taskCount = indexCount;
        String word1;
        String word2;
        if (taskCount == 1){
            word1 = "is ";
            word2 = " task";
        } else {
            word1 = "are ";
            word2 = " tasks";
        }
        System.out.println("Got it. I've added this task:\n " + this);
        System.out.println("Now there " + word1 + indexCount + word2 + " in your list");
    }
}
