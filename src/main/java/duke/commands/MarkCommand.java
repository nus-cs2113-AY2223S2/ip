package duke.commands;

import duke.main.Duke;
import duke.task.Task;
import duke.task.TaskList;

public class MarkCommand extends Command{

    @Override
    public void handleCommand(String line, TaskList taskList){
        int indexCount = Task.getIndexCount();
        int indexSelect;
        String lineBreak = UI.getLineBreak();
        try {

            String done = line.split(" ")[1];
            if (indexCount == 0) {
                DukeException.emptyList();
                return;
            }
            indexSelect = Integer.parseInt(done) - 1;

            if (indexSelect < 0 || indexSelect > indexCount) {
                DukeException.printError();
                return;
            }
            taskList.getTask(indexSelect).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskList.getTask(indexSelect));
            System.out.println(lineBreak);
        } catch (Exception e) {
            DukeException.printError();
            System.out.println(lineBreak);
        }
    }
}
