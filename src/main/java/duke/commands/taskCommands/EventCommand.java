package duke.commands.taskCommands;

import duke.commands.Command;
import duke.exceptions.InvalidTaskException;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import static duke.constants.Constants.LINEBREAK;

public class EventCommand extends Command {

    @Override
    public void handleCommand(String line, TaskList taskList){
        int markIndex;
        int markIndex1;
        int indexCount = Task.getIndexCount();
        String description;

        try {
            String from;
            String to;

            description = line.substring(line.indexOf(' ') + 1, line.indexOf('/')).trim();
            markIndex = line.indexOf("/from");
            markIndex1 = line.indexOf("/to");
            if (markIndex == -1 || markIndex1 == -1) {
                throw new InvalidTaskException();
            }

            from = line.substring(markIndex + 5, markIndex1).trim();
            to = line.substring(markIndex1 + 3).trim();

            if (from.equals("")) {
                throw new InvalidTaskException();
            }

            ToDo newDeadline = new Events(description, from, to);
            taskList.addTask(newDeadline);

        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        }
    }
}
