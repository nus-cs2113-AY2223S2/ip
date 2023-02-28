package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

public class IndexCommand extends Command {
    protected int taskNum;
    public IndexCommand(String commandType, int taskNum){
        super(commandType);
        this.taskNum = taskNum;
    }
    @Override
    public void execute(TaskList tasks) {
        switch (commandType){
            case "mark":
                UI.printMessage("Nice! I've marked this task as done:");
                tasks.mark(taskNum);
                UI.printMarkMessage(tasks.getStatus(taskNum), tasks.getDescription(taskNum));
                break;
            case "unmark":
                UI.printMessage("OK, I've marked this task as not done yet:");
                tasks.unmark(taskNum);
                UI.printMarkMessage(tasks.getStatus(taskNum), tasks.getDescription(taskNum));
                break;
            case "delete":
                UI.printMessage("Noted. I've removed this task:");
                UI.printMessage(" " + tasks.getString(taskNum));
                tasks.removeTask(taskNum);
                UI.printTaskListLength(tasks.sizeOfList());
                break;
        }
        Storage.updateFile(tasks);
    }
}
