package duke.commands.taskCommands;

import duke.commands.Command;
import duke.exceptions.InvalidTaskException;
import duke.save.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import static duke.constants.Constants.LINEBREAK;

public class DeadlineCommand extends Command {

    @Override
    public void handleCommand(String line, TaskList taskList, Storage storage) {
        int markIndex;
        String description;
        LocalDate deadline;

        try {
            description = line.substring(line.indexOf(' ') + 1, line.indexOf('/')).trim();
            markIndex = line.indexOf("/by");
            if (markIndex == -1) {
                throw new InvalidTaskException();
            }

            String deadlineString = line.substring(markIndex + 3).trim();
            if (deadlineString.equals("")) {
                throw new InvalidTaskException();
            }

            try {
                deadline = LocalDate.parse(deadlineString);
                ToDo newDeadline = new Deadline(description, deadline);
                taskList.addTask(newDeadline);
                storage.updateFile(taskList);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.\n" + LINEBREAK);
            }




        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }
}
