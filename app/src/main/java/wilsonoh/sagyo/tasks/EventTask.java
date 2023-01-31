package wilsonoh.sagyo.tasks;

import wilsonoh.sagyo.commands.CommandType;

public class EventTask extends Task {

    private String from;
    private String to;

    public EventTask(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskType() {
        return CommandType.EVENT.name().toLowerCase();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
