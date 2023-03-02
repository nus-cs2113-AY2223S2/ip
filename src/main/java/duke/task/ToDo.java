package duke.task;

public class ToDo extends Task {

    /**
     * Initial constructor for ToDo task. Status is set to false (undone) by default.
     *
     * @param description description of the task content
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for event task when task status is given.
     *
     * @param description description of the ToDo task content
     * @param isDone status of the ToDO=o task. True if done and false if not done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }


    /**
     * Returns todo task with all the details.
     *
     * @return Full todo task in the format of "[T][status symbol] Task description".
     */
    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }
}
