package duke;

import duke.storage.Storage;

public class IndexCommand extends Command{
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
                System.out.println("  [" + tasks.getStatus(taskNum)+ "] " + tasks.getDescription(taskNum));
                break;
            case "unmark":
                UI.printMessage("OK, I've marked this task as not done yet:");
                tasks.unmark(taskNum);
                System.out.println("  [" + tasks.getStatus(taskNum)+ "] " + tasks.getDescription(taskNum));
                break;
            case "delete":
                UI.printMessage("Noted. I've removed this task:");
                System.out.println("  " + tasks.getString(taskNum));
                tasks.removeTask(taskNum);
                UI.printTaskList(tasks.sizeOfList());
                break;
        }
        Storage.updateFile(tasks);
    }
}
