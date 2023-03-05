/**
 * A ToDos task represents a task to be done.
 */
public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    /**
     * This method is used to get the ToDos icon.
     *
     * @return String This returns ToDos icon.
     */
    public String getIcon() {
        return StrIntLib.toDoIcon;
    }
}
