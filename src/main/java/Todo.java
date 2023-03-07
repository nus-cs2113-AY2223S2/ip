/**
 * Represents a todo type of task
 */
public class Todo extends Task {

    private String smallSpace;
    private String bigSpace;

    public Todo(String description) {
        super(description);
        Ui ui = new Ui();
        this.smallSpace = ui.SMALL_SPACE;
        this.bigSpace = ui.BIG_SPACE;
    }

    /**
     * Returns the letter denoting the type of task.
     *
     * @return "T" denoting Todo.
     */
    @Override
    public String getTypeOfTask() {
        return "T";
    }

    /**
     * Returns the formatted string to be displayed to user without prepended information.
     *
     * @return formatted todo information for user.
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * Returns the formatted string to be saved in duke.txt.
     *
     * @return formatted todo information.
     */
    @Override
    public String getDetailsToSave() {
        return super.description;
    }

    /**
     * Returns the formatted string to be displayed to user.
     *
     * @return formatted todo information for user.
     */
    public String toString() {
        return this.bigSpace + "[T][ ] " + super.description;
    }

}
