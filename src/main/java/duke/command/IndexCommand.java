package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

/**
 * Command when dealing with tasks that requires numbers
 */
public class IndexCommand extends Command {
    protected int taskNum;
    public IndexCommand(String commandType, int taskNum){
        super(commandType);
        this.taskNum = taskNum;
    }


    /**
     * Executes the command according to command type
     *
     * @param tasks tasklist which contains all the tasks
     */
    @Override
    public void execute(TaskList tasks) {
        switch (commandType){
            case "mark":
                tasks.mark(taskNum);
                System.out.println("Nice! I've marked this task as done:");
                UI.printMarkMessage(tasks, taskNum);
                break;
            case "unmark":
                tasks.unmark(taskNum);
                System.out.println("OK, I've marked this task as not done yet:");
                UI.printMarkMessage(tasks, taskNum);
                break;
            case "delete":
                tasks.removeTask(taskNum);
                UI.printDeleteMessage(tasks, taskNum);
                break;
        }
        Storage.updateFile(tasks);
    }
}
