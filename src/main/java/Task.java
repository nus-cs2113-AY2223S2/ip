public class Task {
    private String taskName;
    private boolean isDone;
    private CheckBox checkBox;
    private int index;

    public Task(String name, int index) {
        this.taskName = name;
        this.index = index;
        this.checkBox = new CheckBox();
        this.isDone = false;

    }

    public String getTaskName() {
        return taskName;
    }

    public void printTaskName() {
        checkBox.printCheckBox();
        System.out.println(taskName);
    }

    public void markTask() {
        if (!isDone) {
            isDone = true;
            checkBox.markCheckBox();
        }
    }

    public void unmarkTask() {
        if (isDone) {
            isDone = false;
            checkBox.unmarkCheckBox();
        }
    }
}
