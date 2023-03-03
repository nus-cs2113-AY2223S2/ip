package duke.tasks;

/**
 * Parent class for all tasks created. Can be initialised as a generic task
 */
public class Task {
    private static final String MARKER = "X";
    private boolean isCompleted;
    private final String taskName;
    private final TaskEnum taskType;

    /**
     * Initialise the Task object.
     *
     * @param taskName Description of the task
     * @param taskType TaskEnum, for deserializing from JSON
     */
    public Task(String taskName, TaskEnum taskType) {
        this.taskName = taskName;
        isCompleted = false;
        this.taskType = taskType;
    }

    /**
     * Describes the task with its status and description.
     *
     * @return String describing the task
     */
    public String describeTask() {
        return getCheckbox(isCompleted) + " " + taskName;
    }

    /**
     * A helper function which visualises the task status, uses the default marker X.
     *
     * @param isChecked Status of the task
     * @return String visualising if the task is checked [X] or not [ ]
     */
    public static String getCheckbox(boolean isChecked) {
        return "[" + (isChecked ? MARKER : " ") + "]";
    }

    /**
     * A helper function which visualises the task status, uses the marker provided.
     * Is also used for showing the type of the task - [T], [D], [E].
     *
     * @param isChecked Status of the checkbox
     * @param checkboxMarker  Marker to be shown
     * @return String showing checkbox with marker if true, without if false
     */
    public static String getCheckbox(boolean isChecked, String checkboxMarker) {
        return "[" + (isChecked ? checkboxMarker : " ") + "]";
    }

    /**
     * Set whether the task is completed or not.
     *
     * @param taskState true if task is completed, false otherwise
     */
    public void setIsCompleted(boolean taskState) {
        isCompleted = taskState;
    }
}
