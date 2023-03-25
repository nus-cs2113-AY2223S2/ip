/**
 * Deadline class that contains type, status, dueDate, and thingToDo that describe the task
 */

package duke.Tasks;

import duke.Tasks.Task;

public class Deadline extends Task {

    protected String dueDate;
    protected String thingToDo;

    public Deadline(String type, String status, String description) {
        super(type, status, description);
        int index = description.indexOf("/");
        dueDate = description.substring(index + 1);
        thingToDo = description.substring(0, index - 1);
    }

    public String getDueDate() {
        return dueDate;
    }

    /**
     * Returns a string displaying the Deadline task details
     *
     * @return printFormat in the form of [D] [ ] task (by dueDate)
     */
    @Override
    public String getPrintFormat() {
        return type + " " + status + " " + thingToDo + " (" + dueDate + ")";
    }


}
