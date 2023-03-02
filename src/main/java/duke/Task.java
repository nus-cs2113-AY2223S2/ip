package duke;

/*
The Task class represents a task with a name, a completion status, and a task type.
*/
public class Task {
    private String name;
    private boolean isDone;
    private char taskType;

    /**
     * Returns the completion status of the task.
     * 
     * @return a boolean representing the completion status of the task.
     */
    public boolean isIsDone() {
        return this.isDone;
    }

    /**
     * Returns the type of the task.
     * 
     * @return a char representing the type of the task.
     */
    public char getTaskType() {
        return this.taskType;
    }

    /*
     * Sets the type of the task.
     * 
     * @param taskType a char representing the type of the task.
     */
    public void setTaskType(char taskType) {
        this.taskType = taskType;
    }

    /*
     * Creates a new task with a given name and completion status.
     * 
     * @param name a String representing the name of the task.
     * 
     * @param isDone a boolean representing the completion status of the task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns the name of the task.
     * 
     * @return a String representing the name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the task.
     * 
     * @param name a String representing the name of the task.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the completion status of the task.
     * 
     * @return a boolean representing the completion status of the task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the completion status of the task.
     * 
     * @return a boolean representing the completion status of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Sets the completion status of the task.
     * 
     * @param isDone a boolean representing the completion status of the task.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a String representation of the task.
     * 
     * @return a String representing the task.
     */
    public String toString() {
        if (this.isDone == true) {
            return " [T][X] " + this.name;
        }
        return " [T][ ] " + this.name;
    }

    /**
     * Prints the task to the console.
     */
    public void print() {
        if (this.isDone == false) {
            System.out.println("." + this.toString());
        } else {
            System.out.println("." + this.toString());
        }
    }

    /**
     * Searches the name of the task for a given String.
     * 
     * @param s a String representing the search query.
     * 
     * @return a boolean indicating whether the search query was found in the name
     *         of the task.
     */
    public boolean find(String s) {
        return this.name.contains(s);
    }

}
