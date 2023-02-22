package duke.commands.taskCommands;

import duke.commands.Command;
import duke.exceptions.InvalidTaskException;
import duke.save.Storage;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

import static duke.constants.Constants.LINEBREAK;

public class EventCommand extends Command {

    @Override
    public void handleCommand(String line, TaskList taskList, Storage storage) {
        int markIndex;
        int markIndex1;
        int indexCount = Task.getIndexCount();
        String description;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            String fromString;
            String toString;

            LocalDateTime from;
            LocalDateTime to;

            description = line.substring(line.indexOf(' ') + 1, line.indexOf('/')).trim();
            markIndex = line.indexOf("/from");
            markIndex1 = line.indexOf("/to");
            if (markIndex == -1 || markIndex1 == -1) {
                throw new InvalidTaskException();
            }

            fromString = line.substring(markIndex + 5, markIndex1).trim();
            toString = line.substring(markIndex1 + 3).trim();

            if (fromString.equals("") || toString.equals("")) {
                throw new InvalidTaskException();
            }

            from = LocalDateTime.parse(fromString, formatter);
            to = LocalDateTime.parse(toString, formatter);
            ToDo newDeadline = new Event(description, from, to);
            taskList.addTask(newDeadline);
            storage.updateFile(taskList);


        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format. Please try again.\n" + LINEBREAK);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Invalid Command. Please try again.\n" + LINEBREAK);
        }
    }
}
