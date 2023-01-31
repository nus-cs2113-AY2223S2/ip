package wilsonoh.sagyo.tasks;

import wilsonoh.sagyo.commands.CommandType;

public class DeadlineTask extends Task {

    private String by;

    public DeadlineTask(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return CommandType.DEADLINE.name().toLowerCase();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
