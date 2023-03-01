/**
 * Contains methods to build up a complete Task
 *
 * @param description A string that details the description of the task
 * @param isDone boolean variable that identifies whether the task is completed
 * @param toDo A string that identifies the Task as a Todo task
 */

package duke.tasks;

import duke.Task;

public class ToDo extends Task {
    protected String toDo;

    public ToDo(String description, boolean isDone, String toDo) {
        super(description, isDone);
        this.toDo = toDo;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }
}
