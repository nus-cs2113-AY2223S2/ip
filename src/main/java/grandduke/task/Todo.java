package grandduke.task;

public class Todo extends Task {
    public Todo(String taskDesc) {
        super(taskDesc);
    }

    @Override
    public String getTaskPrint() {
        return "[T]" + super.getTaskPrint();
    }
}
