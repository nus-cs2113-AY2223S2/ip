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
    }

    public void unmarkDone() {
        isDone = false;
    }


    /**
     * Determine if the keyword is found within the task.
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

    public String status(){
        if(isDone) {
            return "X";
        }
        else {
            return " ";
        }
    }

    public String getStatus(){
        if(isDone) {
            return "1";
        }
        else {
            return "O";
        }
    }

    public String saveToFile() {
        return "";
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
