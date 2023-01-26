/**
 * The Task class represents real-world tasks with content and finish status.
 */
public class Task {

    private String content;
    private boolean finished;

    /**
     * Default constructor for the Task class.
     * Initialize task content to be empty and set finished to be false.
     */
    public Task() {
        this("", false);
    }

    /**
     * Another constructor for the Task class.
     * Initialize task content to be the parameter and set finished to be false.
     * @param content content of the task
     */
    public Task(String content) {
        this(content, false);
    }

    /**
     * Another constructor for the Task class.
     * @param content content of the task.
     * @param finished finish status of the task.
     */
    public Task(String content, boolean finished) {
        this.content = content;
        this.finished = finished;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFinished() {
        return finished;
    }

    /**
     * Prints the finish status of the task following by the content.
     */
    public void printTask() {
        System.out.println(this);
    }

    /**
     * Marks the finish status of the task, i.e. set finished to be true.
     * Prints a reply message after successfully mark the task.
     * Prints an error message if the task is already marked.
     */
    public void mark() {
        if (!finished) {
            finished = true;
            System.out.println("\t Nice! I've marked this task as done:");
            System.out.println("\t " + this);
        } else {
            System.err.println("\t The task is already marked!");
        }
    }

    /**
     * Unmarks the finish status of the task, i.e. set finished to be false.
     * Prints a reply message after successfully unmark the task.
     * Prints an error message if the task is already unchecked.
     */
    public void unmark() {
        if (finished) {
            finished = false;
            System.out.println("\t OK, I've marked this task as not done yet:");
            System.out.println("\t " + this);
        } else {
            System.err.println("\t This task is already not marked!");
        }
    }

    /**
     * Converts the task to a string containing finished status and content.
     * e.g. For finished task: [X] a finished task.
     * e.g. For unfinished task: [ ] an unfinished task.
     * @return a string containing finished status and task content
     */
    @Override
    public String toString() {
        return "[" + (finished ? "X" : " ") + "]" + " " + content;
    }
}
