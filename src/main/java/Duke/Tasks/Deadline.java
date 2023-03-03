package Duke.Tasks;

public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    /***
     * Outputs task type
     * @return String representing task type.
     */
    @Override
    public String getTaskType() {
        return "D";
    }
    /**
     * Prints information of task with format.
     */
    @Override
    public void printTask() {
        System.out.print("[");
        System.out.print(getTaskType());
        System.out.print("][");
        if (super.isDone) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }
        System.out.println("] " + this.getTaskName() + "(by:" + dueDate + ")");
    }

    /***
     * Outputs a formatted String containing information of the task saved in a text file.
     * @return String containing information of the task.
     */
    @Override
    public String saveInfo() {
        return getTaskType() + "t/" +
                (isDone ? "X" : "Y") + "m/" +
                taskName + "n/" +
                dueDate + "\n";
    }
}
