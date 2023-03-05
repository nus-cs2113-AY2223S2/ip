package buddy.commands.addTaskCommands;

import buddy.exceptions.InvalidCommandException;
import buddy.commands.Command;
import buddy.tasks.TaskList;
import buddy.tasks.Event;


public class AddEventCommand extends Command {

    /**
     * Process Event command by user and adds event
     * Throws exception if command is in wrong format
     *
     * @param taskList List of tasks
     * @param input    Command inputted by user
     */
    @Override
    public void executeCommand(TaskList taskList, String input) {
        try {
            if ( !(input.contains("/from") && input.contains("/to")) ){
                throw new InvalidCommandException();
            }
            String[] eventSplit = input.split("/", 3);
            String[] eventAndName = eventSplit[0].split(" ", '2');
            String eventName = eventAndName[1];
            String[] fromAndStart = eventSplit[1].split(" ", '2');
            String start = fromAndStart[1].trim();
            String[] toAndEnd = eventSplit[2].split(" ", '2');
            String end = toAndEnd[1].trim();
            if (eventName.equals("") || start.equals("") || end.equals("")) {
                throw new InvalidCommandException();
            }
            Event eventBeingAdded = new Event(eventName, start, end);
            taskList.addTask(eventBeingAdded);

        } catch (InvalidCommandException e) {
            InvalidCommandException.printMessage();
        }

    }
}
