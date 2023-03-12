package alltasks;

/**
 * This Todo class represents tasks without due deadlines that are added by users to Coffee Bot.
 * Coffee Bot keeps track of the todo tasks.
 */
public class ToDo extends Task{
    /**
     * Creates a Todo class from the input command.
     *
     * @param descriptive description of input command.
     */
    public ToDo(String descriptive) {
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
    //Solution below adapted and reused from Student Oh Yi Xiu Wilson
    // with modifications made by Wilson Lee Jun Wei
    @Override
    public String getInfo() {
        return String.format("%s|%s|%s", "Todo", this.isDone ? 1 : 0, this.description);
    }
    //@@ Student Oh Yi Xiu Wilson
}
