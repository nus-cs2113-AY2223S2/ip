package elzi.task;

import elzi.ElziException;

/**
 * @author : Steven A. O. Waskito
 * Task parent class that has attribute
 * Description, isDone
 **/
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task object with attribute: description, isDone
     * @param description task description
     * @throws ElziException if description is empty
     */
    public Task(String description) throws ElziException {
        if (description.isBlank()) {
            throw new ElziException("Description can't be empty!");
        }
        this.description = description;
        isDone = false;
    }
    public String getType() {
        return "";
    }
    public void setAsDone() {
        this.isDone = true;
    }
    public void setAsNotDone() {
        this.isDone = false;
    }
    /**
     * Method return true if task contains keyword
     */
    public boolean contains(String keyword) {
        if (description.toLowerCase().contains(keyword.toLowerCase())) {
            return true;
        }
        return false;
    }
    /**
     * Method encoding the given task to save
     */
    public String encode() {
        return "";
    }
}
