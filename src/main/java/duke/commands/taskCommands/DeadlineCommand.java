package duke.commands.taskCommands;

import duke.commands.Command;
import duke.commands.DukeException;
import duke.commands.UI;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class DeadlineCommand extends Command {

    @Override
    public void handleCommand(String line, TaskList taskList){
        String lineBreak = UI.getLineBreak();
        int markIndex;
        int indexCount = Task.getIndexCount();
        String description;
        String deadline;

        try {
            description = line.substring(line.indexOf(' ') + 1, line.indexOf('/')).trim();
            markIndex = line.indexOf("/by");
            if (markIndex == -1) {
                DukeException.printError();
                System.out.println(lineBreak);
                return;
            }

            deadline = line.substring(markIndex + 3).trim();

            if (deadline.equals("")) {
                DukeException.printError();
                System.out.println(lineBreak);
                return;
            }

            ToDo newDeadline = new Deadline(description, deadline);
            taskList.addTask(indexCount, newDeadline);
            newDeadline.printAdded();
            System.out.println(lineBreak);
        } catch (Exception e) {
            DukeException.printError();
            System.out.println(lineBreak);
        }
    }
}
