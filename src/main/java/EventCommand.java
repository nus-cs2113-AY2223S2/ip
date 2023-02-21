/**
 * This class represents the command to add an event task to the task list.
 * It is a subclass of the Command class.
 * It contains the execute method to add the event task to the task list.
 * It also contains the isExit method to return false.
 * @param taskName the name of the task
 * @param eventTime the event time of the task
 * @param execute method to execute the command
 * @param isExit method to return false if the command is not an exit command
 * @throws DukeException if the event time is not in the correct format
 */

public class EventCommand extends Command {
    
    protected String taskName;
    protected String eventTime;

    /**
     * This constructor takes in the task name and event time.
     * @param taskName the name of the task
     * @param eventTime the event time of the task
     */
    public EventCommand(String taskName, String eventTime){
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

    /**
     * This method executes the event command.
     * @param tasks the task list
     * @param ui the user interface
     * @throws DukeException if the event time is not in the correct format
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException{
        Event event = new Event(taskName, eventTime);
        tasks.add(event);
        ui.printAddedTask(event, tasks.size());
    }
    
    /**
     * This method checks if the command is an exit command.
     * @return false if the command is not an exit command
     */
    public boolean isExit(){
        return false;
    }
}