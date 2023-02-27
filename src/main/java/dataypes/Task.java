package dataypes;

/**
 * This is the class which represents a Class with a {@link Task#description} and completion status {@link Task#isDone}
 *
 * @author Muthya Narayanachary Akhil
 */
public class Task  implements TaskFileHandler {
    protected String description;
    protected boolean isDone;

    /**
     * An empty constructor of {@link Task}
     */
    public Task() {}

    /**
     * A constructor for {@link Task} which takes in the {@link Task#description} and sets it, while ensuring that the
     * {@link Task#isDone} is set to false.
     *
     * @param description The description of the {@link Task}.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns {@link Task#isDone}
     *
     * @return Returns {@link Task#isDone} which signifies if the task is done or not
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * This returns a {@link String} icon based on the {@link Task#isDone} value.
     *
     * @return Either <code>[X] or [ ]</code> based on the {@link Task#isDone} value.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns the {@link Task#description} of the {@link Task} object.
     *
     * @return A {@link String} which is the description of the {@link Task} object
     * */
    public String getDescription() {
        return this.description;
    }

    /**
     * This returns the status and description used in {@link corefunctionalities.Ui} to display objects of type
     * {@link Task}.
     *
     * @return A formatted {@link String} containing the {@link Task#description} and {@link Task#isDone}.
     */
    public String getStatusAndDescription() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    /**
     * Marks the task as <code>Done</code> by toggling {@link Task#isDone} to true;
     */
    public void markTask() {
        this.isDone = true;
    }
    /**
     * Unarks the task as <code>Done</code> by toggling {@link Task#isDone} to false;
     */
    public void unMarkTask() {
        this.isDone = false;
    }

    /**
     * Returns a {@link String} which can be added to the file and
     * later re-read to populate the {@link corefunctionalities.TaskList}
     *
     * @return A {@link String} formatted for entry into the file
     */
    public String enCode() {
        return getStatus() + " # " + getDescription();
    }

    /**
     * This method sets the description of the Object based on the input.
     *
     * @param description The {@link Task#description} of the {@link Task} object
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
