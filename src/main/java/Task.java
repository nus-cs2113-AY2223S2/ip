/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 *
 * This is the class Task that accepts description,
 *      but used only as parent class to Event, Deadline, and Todo Class
 *
 **/
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }
    public String getDescription() {
        return description;
    }

    /**
     * Overrides the print function of the class
     */
    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + description;
    }
    /**
     * Overrides the getUpdate method that is used to save/update the input.txt file
     * @return
     */
    public String getUpdate() {
        return (description);
    }
}
