package tasks;

/**
 * Represents Todo task
 */
public class Todo extends Task{
    /**
     * @param name
     * @param isDone
     */
    public Todo(String name, Boolean isDone) {
        super(name, isDone);
    }

    /**
     * @return String representation of the Todo type
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * @return String representation of the todo for the CLI output
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @return String representation of the todo for the file output
     */
    @Override
    public String getFileFormatString() {
        return String.format("%s | %d | %s",
                this.getType(),
                this.isDone ? 1 : 0,
                this.name
        );
    }
}
