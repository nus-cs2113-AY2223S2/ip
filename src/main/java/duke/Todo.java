package duke;
/**
 * Basic task that needs to be done.
 */
public class Todo extends Task{
    protected boolean isDone;
    public Todo(String description) {
        super(description);
        this.isDone = false;

    }
    /**
     * Returns icon matching status type
     * @return icon that depicts status of done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
    /**
     * Returns icon matching todo type
     * @return icon of type todo
     */
    public String getTypeIcon() {
        return ("[T]"); // mark done task with X
    }
    public String getInfo() {
        return "";
    }
    public void setDone(boolean d) {
        this.isDone = d;
    }
}
