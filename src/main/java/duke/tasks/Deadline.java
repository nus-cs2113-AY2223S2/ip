package duke.tasks;
/**
 * Represents a Deadline task in Duke, which is a subclass of Task.
 */
public class Deadline extends Task {

    public String deadline;

    /**
     * Deadline constructor which calls the superclass constructor and initialises
     * deadline attribute of its own.
     *
     * @param name        Name of Deadline task
     * @param deadline    User specified deadline of task
     * @param isCompleted Completion status of task
     */
    public Deadline(String name, String deadline, Boolean isCompleted) {
        super(name, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String toTextFileFormat(){
        return "deadline/" + name + "/" + isCompleted + "/" + deadline;
    }
}
