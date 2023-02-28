package chronos.tasktype;

import chronos.exceptions.ChronosExceptions;
import chronos.savehandler.Save;

/**
 * Represents the Deadline type task, a subclass of Task
 */
public class Deadline extends Task {
    //the deadline of the user-specified task
    private String due;

    /**
     * Constructs a new Deadline object
     * @param description The description of deadline task
     * @param due         The deadline of the task
     */
    public Deadline(String description, String due) {
        super(description);
        try {
            if (due == null||description == null) {
                throw new ChronosExceptions(null);
            }
            this.due = due;
        } catch (ChronosExceptions e) {
            System.err.println("DESCRIPTION OR DUE DATE IS MISSING. PLEASE VIEW HELP MENU FOR PROPER FORMAT. REMOVE THE INVALID TASK");
        }
    }

    /**
     * Constructs a new {@link Deadline} object to be used for writing to the hard-disk file
     * @param description   The description of the deadline task
     * @param due           The deadline of the task
     * @param isDone        The completion status of the task
     */
    public Deadline(String description, String due, boolean isDone) {
        super(isDone, description);
        try {
            if (due == null || description == null) {
                throw new ChronosExceptions(null);
            }
            this.due = due;
        } catch(ChronosExceptions error){
            System.err.println("DESCRIPTION OR DUE DATE IS MISSING. PLEASE VIEW HELP MENU FOR PROPER FORMAT. REMOVE THE INVALID TASK");
        }
    }



    public String getDue() {
        return due;
    }


    /**
     * Formats the deadline task for display in the command line interface
     * @return The string representation of a deadline task
     */
    public String toString() {

        return String.format("[D] %s (DUE: %s)", super.toString(), getDue());
    }

    /**
     * @param taskType
     * @return The String representation of a deadline task in the plain text file
     */
    @Override
    public Save toSave(String taskType) {
        return new Save(taskType, isDone(), getDescription(), due, "", "");
    }
}
