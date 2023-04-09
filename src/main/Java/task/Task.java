package task;

/**
 * Abstract class to implement Task class.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Build constructor for Task class.
     * @param description the description of the task.
     */
    public Task(String description) {
        setDescription(description);
        this.isDone = false;
    }

    /**
     * Method to get the description of the task.
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to set the description.
     * @param description the description of the class.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method to set the status of the task.
     * @param done boolean.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Method to get the status icon of the task.
     * @return X if task is done, whitespace otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method to get the status of the task.
     * @return String represent 1 for done, 0 for undone.
     */
    public String getIsDone() {
        return (isDone ? "1" : "0");
    }

    /**
     * Method to compute the literal of the date.
     * @param number the date.
     * @return the literal of the date.
     */
    public String nthNumber(int number) {
        if (number > 0) {
            int lastDigit = number % 10;
            int secondLastDigit = (number / 10) % 10;

            if (secondLastDigit == 1) {
                return "th";
            } else {
                switch (lastDigit) {
                    case 1:
                        return "st";
                    case 2:
                        return "nd";
                    case 3:
                        return "rd";
                    default:
                        return "th";
                }
            }
        } else {
            return "";
        }
    }
}
