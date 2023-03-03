package buddy.commands.addTaskCommands;

import buddy.exceptions.InvalidCommandException;
import buddy.commands.Command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import buddy.messages.Messages;
import buddy.tasks.TaskList;
import buddy.tasks.Deadline;


public class AddDeadlineCommand extends Command {

    /**
     * Process Deadline command by user and adds deadline
     *
     * @param taskList List of tasks
     * @param input    Command inputted by user
     */
    @Override
    public void executeCommand(TaskList taskList, String input) {
        try {
            String[] deadlineSplit = input.split("/by", 2);
            String deadlineBy = deadlineSplit[1].trim();
            LocalDate toFormat = LocalDate.parse(deadlineBy);
            if (toFormat.isBefore(LocalDate.now())) {                                                // if deadline date is before today's date, invalid task
                throw new InvalidCommandException();
            }
            String deadlineByFormatted = toFormat.format(DateTimeFormatter.ofPattern("MMM d yyyy")); // Deadline must be in this format
            String[] deadlineAndName = deadlineSplit[0].split(" ", 2);
            String deadlineName = deadlineAndName[1].trim();
            if (deadlineName.equals("")) {
                throw new InvalidCommandException();                                                 // if no deadline description inputted, invalid task
            }
            Deadline deadlineBeingAdded = new Deadline(deadlineName, deadlineByFormatted);
            taskList.addTask(deadlineBeingAdded);

        } catch (InvalidCommandException e) {
            InvalidCommandException.printMessage();
        } catch (DateTimeParseException e) {
            System.out.println(Messages.DIVIDER);
            System.out.println("This is the wrong format of deadline date! Please input deadline <description> <date> where <date> is in YYYY-MM-DD!");
            System.out.println(Messages.DIVIDER);
        }
    }
}