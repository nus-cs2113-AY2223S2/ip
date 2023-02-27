package duke.command;

import duke.task.Task;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Initialises the event from the user command.
     *
     * @param taskDescription String of description of task to be added.
     * @param from Start date or time of the event.
     * @param to End date or time of the event.
     */
    public Event(String taskDescription, String from, String to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
        setCommand("event " + taskDescription + " /from " + from + " /to " + to);
    }

    /**
     * Makes a format of string to be printed.
     *
     * @return String of deadline with format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
