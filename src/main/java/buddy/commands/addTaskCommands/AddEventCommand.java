package buddy.commands.addTaskCommands;

import buddy.exception.InvalidCommandException;
import buddy.commands.Command;
import buddy.tasks.*;

public class AddEventCommand extends Command {
    @Override
    public void executeCommand(TaskList taskList, String input) {
        try{
            String[] eventSplit = input.split("/", 3);
            String[] eventAndName= eventSplit[0].split(" ", '2');
            String eventName = eventAndName[1];
            String[] fromAndStart = eventSplit[1].split(" ", '2');
            String start = fromAndStart[1].trim();
            String[] toAndEnd = eventSplit[2].split(" ", '2');
            String end = toAndEnd[1].trim();
            if (eventName.equals("") || start.equals("") || end.equals("")){
                throw new InvalidCommandException();
            }
            Event eventBeingAdded = new Event(eventName, start, end);
            taskList.addTask(eventBeingAdded);


        }catch(InvalidCommandException e){
            InvalidCommandException.printMessage();
        }

    }
}
