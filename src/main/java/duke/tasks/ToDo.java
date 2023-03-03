package duke.tasks;

import duke.constants.Config;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description, TaskEnum.TODO);
    }

    /**
     * Describes the task with its task type, status and description.
     *
     * @return String describing the task
     */
    @Override
    public String describeTask() {
        return getCheckbox(true, Config.MARKER_TODO) + super.describeTask();
    }
}
