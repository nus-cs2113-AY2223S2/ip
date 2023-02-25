import duke.task.Event;

public class EventCommand extends Command {
    private Event event;

    public void setEvent(String input) {
        String[] temp = input.split(("event | /from | /to ")); //separates event description and times
        String description = temp[1];
        String from = temp[2];
        String to = temp[3];
        this.event = new Event(description, from, to);
    }

    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        try {
            setEvent(input);
            tasks.addTask(event);
            ui.printTaskAddedStatement(tasks, event);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyEventMessage();
        }
    }
}
