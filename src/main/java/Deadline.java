public class Deadline extends Task {

    protected String dueDate;
    Deadline(String taskName, int taskNumber, String dueDate) {
        super(taskName, taskNumber);
        this.dueDate = dueDate;
    }
    @Override
    public void printTaskType() {
        System.out.print("D");
    }
    @Override
    public void printTask() {
        System.out.print("[");
        printTaskType();
        System.out.print("][");
        if (super.isDone) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }
        System.out.println("] " + this.getTaskName() + "(by:" + dueDate + ")");
    }
}
