package duke.commands;

import duke.task.Task;
import duke.task.TaskList;

public class UnmarkCommand extends Command{

    @Override
    public void handleCommand(String line, TaskList taskList) {
        try {
            int indexCount = Task.getIndexCount();
            int indexSelect;
            String lineBreak = UI.getLineBreak();

            String undone = line.split(" ")[1];
            if (indexCount == 0) {
                DukeException.emptyList();
                return;
            }

            indexSelect = Integer.parseInt(undone) - 1;
            if (indexSelect < 0 || indexSelect > indexCount) {
                DukeException.printError();
                return;
            }

            taskList.getTask(indexSelect).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskList.getTask(indexSelect));
            System.out.println(lineBreak);
        } catch (Exception e) {
            DukeException.printError();
        }
    }
}
