// Eventcommand class that is used to create event commands

public class EventCommand extends Command {
    protected String taskName;
    protected String eventTime;
    //constructor that takes in the task name and event time
    public EventCommand(String taskName, String eventTime){
        this.taskName = taskName;
        this.eventTime = eventTime;
    }
    //method that executes the event command
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        Event event = new Event(taskName, eventTime);
        tasks.add(event);
        ui.printAddedTask(event, tasks.size());
    }
    //method that checks if the command is an exit command
    public boolean isExit(){
        return false;
    }
}