package alltasks;

/**
 * This Todo class represents tasks without due deadlines that are added by users to Coffee Bot.
 * Coffee Bot keeps track of the todo tasks.
 */
public class Todo extends Task{

    public Todo(String descriptive) {
        super(descriptive);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "]" + " " + description;
    }

    @Override
    public String getInfo() {
        return String.format("%s|%s|%s", "Todo", this.isDone, this.description);
    }
}
