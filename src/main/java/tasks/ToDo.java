package tasks;

public class ToDo extends Task{

    /**
     * Class constructor with <code>description</code> as
     * the parameter to be initialized.
     *
     * @param description the description the the task to be done.
     */
    public ToDo(String description) {
        super(description, "T");
    }

    @Override
    public String toString() {
        return '[' + super.getType() + "]" + super.toString();
    }

}
