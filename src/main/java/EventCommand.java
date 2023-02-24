import duke.task.Event;

public class EventCommand extends Command{
    private Event event;

    public void setEvent(String input) {
        try {
            String[] temp = input.split(("event | /from | /to ")); //separates event description and times
            String description = temp[1];
            String from = temp[2];
            String to = temp[3];
            this.event = new Event(description, from, to);
        } catch (IndexOutOfBoundsException exception){
            Messages.invalidTaskMessage();
        }
    }

    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        setEvent(input);
        tasks.addTask(event);
        ui.printTaskAddedStatement(tasks, event);
    }
}
