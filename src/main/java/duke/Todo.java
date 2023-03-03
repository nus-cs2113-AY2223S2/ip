package duke;

/**
 * This object represents a task object of Todo type.
 * Todo type contains all the attributes and methods from template Task
 */
public class Todo extends Task {
    /**
     * Initialises a Todo object
     * @param description The description of what the Todo actually is
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the template Task toString with an additional [T] string which symbolizes that it is a Todo task
     * @return Returns the symbol [T], the status icon and the description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Overrides the template Task getSymbol with "[T]" string which symbolizes that it is a Todo task
     * @return Returns the character "T" symbolizes that it is a Todo task
     */
    @Override
    public String getSymbol() {
        return "T";
    }

}
