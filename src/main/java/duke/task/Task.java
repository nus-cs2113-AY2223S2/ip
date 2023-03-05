package duke.task;

/**
 * Task class used to have a text (description) and isDone attributes
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Instantiates defaults empty string description and isDone as false
     */
    public Task() {
        this.description = "";
        this.isDone = false;
    }

    /**
     * Instantiates string description and defaults isDone as false
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Instantiates string description and isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Overrides toString method to return output as shown in list of tasks
     *
     * @return string of task in listed format
     */
    @Override
    public String toString() {
        String descriptionNoSlash = (description.indexOf("/") == -1) ? description : (description.substring(0, description.indexOf("/")));
        return "[" + ((this.isDone) ? "X] " : " ] ") + descriptionNoSlash;
    }

    /**
     * Task description getter
     *
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Task description setter
     *
     * @param description task description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Task isDone getter
     *
     * @return if task if done
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Task isDone setter
     *
     * @param isDone if task if done
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
