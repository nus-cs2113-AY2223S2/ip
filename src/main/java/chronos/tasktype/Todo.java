package chronos.tasktype;

import chronos.savehandler.Save;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    @Override
    public Save toSave(String taskType) {
        return new Save(taskType, isDone(), getDescription(), "", "", "");
    }
}
