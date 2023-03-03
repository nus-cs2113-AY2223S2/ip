package buddy.commands.addTaskCommands;

import buddy.exception.InvalidCommandException;
import buddy.commands.Command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import buddy.tasks.TaskList;
import buddy.tasks.*;


public class AddDeadlineCommand extends Command {

    @Override
    public void executeCommand(TaskList taskList, String input) {
        try{
            String[] deadlineSplit = input.split("/by", 2);
            String deadlineBy = deadlineSplit[1].trim();
            LocalDate toFormat = LocalDate.parse(deadlineBy);
            if (toFormat.isBefore(LocalDate.now())){
                throw new InvalidCommandException();
            }
            String deadlineByFormatted = toFormat.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String[] deadlineAndName = deadlineSplit[0].split(" ", 2);
            String deadlineName = deadlineAndName[1].trim();
            if (deadlineName.equals("")){
                throw new InvalidCommandException();
            }
            Deadline deadlineBeingAdded = new Deadline(deadlineName, deadlineByFormatted);
            taskList.addTask(deadlineBeingAdded);


        }catch(InvalidCommandException e){
            InvalidCommandException.printMessage();
        }


    }
}