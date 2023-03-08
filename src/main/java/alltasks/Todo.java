package alltasks;

/**
 * This Todo class represents tasks without due deadlines that are added by users to Coffee Bot.
 * Coffee Bot keeps track of the todo tasks.
 */
public class Todo extends Task{

    /**
     * Creates a Todo class from the input command.
     *
     * @param descriptive description of input command.
     */
    public Todo(String descriptive) {
        super(descriptive);
    }

    /**
     * Returns the status of completion of the task item
     * and the description of the input command.
     *
     * @return getStatusIcon() status of completion of task item.
     * @return description description of the input command.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "]" + " " + description;
    }
    /**
     * Returns
     *
     * @return
     */
    @Override
    public String getInfo() {
        return String.format("%s|%s|%s", "Todo", this.isDone, this.description);
    }
}
