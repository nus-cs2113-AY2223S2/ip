package wilsonoh.sagyo.tasks;

import wilsonoh.sagyo.commands.CommandType;

public class TodoTask extends Task {

    public TodoTask(String taskName) {
        super(taskName);
    }
    @Override
    public String getTaskType() {
        return CommandType.TODO.name().toLowerCase();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
