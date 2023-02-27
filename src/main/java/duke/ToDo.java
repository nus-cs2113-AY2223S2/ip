package duke;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public void getTaskStatus() {
        System.out.printf("[T][%s] %s\n", this.getDone(), this.getTaskName());
    }

}
