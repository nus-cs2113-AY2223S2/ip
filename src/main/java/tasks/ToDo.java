package tasks;

/**
 * Represents a task with no dates involved. A <code>Todo</code> object correspond
 * to a Task that only have a name.
 */
public class ToDo extends Task {

    public static final String TODO_CLASS = "T";
    public static final String DONE_TODO_INDICATOR = "[T] [X]";
    public static final String NOT_DONE_TODO_INDICATOR = "[T] [ ]";

    /**
     * Construct a Todo Object using the name of the object.
     *
     * @param itemName A String containing the name of the Todo Object.
     */
    public ToDo(String itemName) {
        super(itemName);
        incrementItemCount();
    }

    @Override
    public void printTask() {
        String status;
        if (super.isDone()) {
            status = DONE_TODO_INDICATOR;
        } else {
            status = NOT_DONE_TODO_INDICATOR;
        }
        System.out.println("." + status + " " + this.getItemName());
    }

    @Override
    public void printTaskWithoutId() {
        System.out.println( "  [T] " + this.getItemName());
    }

    @Override
    public String getClassType () {
        return TODO_CLASS;
    }

    @Override
    public String getToStore() {
        return " " + this.getItemName();
    }
}
