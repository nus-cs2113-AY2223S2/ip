package Duke.Tasks;

import Duke.Tasks.Task;

public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

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

    @Override
    public String saveInfo() {
        return getTaskType() + "t/" +
                (isDone ? "X" : "Y") + "m/" +
                taskName + "n/" +
                dueDate + "\n";
    }
}
