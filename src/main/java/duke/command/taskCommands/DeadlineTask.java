package duke.command.taskCommands;

import duke.command.Command;
import duke.exception.InvalidTaskException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;
import static duke.tasks.Deadline.dateTimeFormatter;

public class DeadlineTask extends Command {
    @Override
    public void processCommand(TaskList tasksList, String input) throws InvalidTaskException, ParseException, DataFormatException {
        //ex: deadline return book /by 2pm
        String[] task = input.split("\\/(by)", 2);
        if (task[1].trim().isEmpty()) {
            throw new InvalidTaskException();
        }
        try {
            LocalDateTime deadline = LocalDateTime.parse(task[1].trim(), dateTimeFormatter);
            tasksList.addTask(new Deadline(task[0].trim(), deadline));
            taskCount++;
        } catch (DateTimeParseException e) {
            System.out.println("Please enter all command parameter!");
            printHorizontalLine();
        }

    }
}
