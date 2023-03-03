package tasks;

/**
 * The <code>Todo</code> class contains key variables that
 * is present in <code>Task</code> class, the most basic type of
 * task to be tracked in the task list.
 * <p></p>
 * There are also methods that could modify and retrieve the
 * task variables present in the class.
 */
public class ToDo extends Task{

    /**
     * Class constructor with <code>description</code> as
     * the parameter to be initialized.
     *
     * @param description the description of the task to be done.
     */
    public ToDo(String description) {
        super(description, "T");
    }

    @Override
    public String toString() {
        return '[' + super.getType() + "]" + super.toString();
    }

}
