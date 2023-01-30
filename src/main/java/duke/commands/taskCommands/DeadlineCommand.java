package duke.commands.taskCommands;

import duke.commands.Command;
import duke.commands.InvalidTaskException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import static duke.constants.Constants.LINEBREAK;

public class DeadlineCommand extends Command {

    @Override
    public void handleCommand(String line, TaskList taskList){
        int markIndex;
        int indexCount = Task.getIndexCount();
        String description;
        String deadline;

        try {
            description = line.substring(line.indexOf(' ') + 1, line.indexOf('/')).trim();
            markIndex = line.indexOf("/by");
            if (markIndex == -1) {
                throw new InvalidTaskException();
            }

            deadline = line.substring(markIndex + 3).trim();

            if (deadline.equals("")) {
                throw new InvalidTaskException();
            }

            ToDo newDeadline = new Deadline(description, deadline);
            taskList.addTask(indexCount, newDeadline);
            newDeadline.printAdded();
            System.out.println(LINEBREAK);
        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        }
    }
}
