package duke.tasklist.task;

import duke.tasklist.exception.DuplicateMarkException;
import duke.tasklist.exception.DuplicateTaskException;

/**
 * The Task class represents real-world tasks with content and marking status.
 */
public abstract class Task {

    protected String content;
    protected boolean isMarked;

    /**
     * Default constructor for the Task class.
     * Initialize task content to be empty and set isMarked to be false.
     */
    public Task() {
        this("", false);
    }

    /**
     * Another constructor for the Task class.
     * Initialize task content to be the parameter and set isMarked to be false.
     * @param content content of the task
     */
    public Task(String content) {
        this(content, false);
    }

    /**
     * Another constructor for the Task class.
     * @param content content of the task.
     * @param isMarked marking status of the task.
     */
    public Task(String content, boolean isMarked) {
        this.content = content;
        this.isMarked = isMarked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getMarked() {
        return isMarked;
    }

    /**
     * Prints the marking status of the task following by the content.
     */
    public void printTask() {
        System.out.println(this);
    }

    /**
     * Marks the marking status of the task, i.e. set isMarked to be true.
     * Prints a reply message after successfully mark the task.
     * Prints an error message if the task is already marked.
     */
    public void mark() throws DuplicateMarkException {
        if (!isMarked) {
            isMarked = true;
        } else {
            throw new DuplicateMarkException();
        }
    }

    /**
     * Unmarks the marking status of the task, i.e. set isMarked to be false.
     * Prints a reply message after successfully unmark the task.
     * Prints an error message if the task is already unchecked.
     */
    public void unmark() throws DuplicateMarkException {
        if (isMarked) {
            isMarked = false;
        } else {
            throw new DuplicateMarkException();
        }
    }

    public String toCSV() {
        return (isMarked ? "1" : "0") + "," +
                "\"" + content + "\"";
    }

    /**
     * Converts the task to a string containing marking status and content.
     * e.g. For a marked task: [X] a marked task.
     * e.g. For an unmarked task: [ ] an unmarked task.
     * @return a string containing marking status and task content
     */
    @Override
    public String toString() {
        return "[" + (isMarked ? "X" : " ") + "]" + " " + content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return obj instanceof Task
                    && (((Task) obj).isMarked && this.isMarked)
                    && (((Task) obj).content.equals(this.content));
        }
    }
}
