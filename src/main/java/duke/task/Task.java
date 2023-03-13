package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Represents the function that will create a Task object according to the user input
     * and mark the task as not done initially when being created.
     *
     * @param description Description of the command that was given by the user.
     */
    protected Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Represents the function that will mark the task as done.
     * It will also inform the user that the task has been successfully marked as done.
     */
    public void markDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" + this);
    }

    /**
     * Represents the function that will mark the task as not done.
     * It will also inform the user that the task has been successfully unmarked as done.
     */
    public void umarkDone() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n" + this);
    }

    /**
     * Represents the function that will determine if the keyword is found within the task,
     *
     * @param partialName Keyword that has to be found within the specified task in the list.
     * @return Returns true if the specified task in the list contains the keyword and false otherwise.
     */
    public boolean findMatch(String partialName) {
        return description.contains(partialName);
    }

    protected String getDescription() {
        return description;
    }

    /**
     * Represents the function that will mark the Task as done
     *
     * @return Returns a string that reflects if the task is done.
     */
    protected String status(){
        if(isDone) {
            return "X";
        }
        else {
            return " ";
        }
    }

    /**
     * Printing the Task that includes whether it is done
     *
     * @return Returns a string that describes the object
     */
    @Override
    public String toString() {
        return "[" + status() + "] " + getDescription();
    }
}
