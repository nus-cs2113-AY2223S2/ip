package duke.commands.taskCommands;

import duke.commands.Command;
import duke.exceptions.DeadlineDateException;
import duke.exceptions.InvalidTaskException;
import duke.save.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.time.LocalDate;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import static duke.constants.Constants.LINEBREAK;

public class DeadlineCommand extends Command {

    /**
     * {@inheritDoc}
     * @throws DeadlineDateException If the date is not in the correct format.
     * @throws InvalidTaskException If the task is not in the correct format.
     * @throws DateTimeParseException If the date is not in the correct format.
     */
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
            if (deadlineString.equals("") || description.equals("")) {
                throw new InvalidTaskException();
            }

            deadline = LocalDate.parse(deadlineString);

            if (deadline.isBefore(LocalDate.now())) {
                throw new DeadlineDateException();
            }

            ToDo newDeadline = new Deadline(description, deadline);
            taskList.addTask(newDeadline);
            storage.updateFile(taskList);

        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Oops, something went wrong!\n" + LINEBREAK);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please try again.\n" + LINEBREAK);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Invalid Command. Please try again.\n" + LINEBREAK);
        } catch (DeadlineDateException e) {
            System.out.println(e.getMessage());
        }
    }
}
