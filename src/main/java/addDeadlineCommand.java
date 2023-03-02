

public class addDeadlineCommand extends Command{
    @Override
    public void executeCommand(TaskList taskList, String input) {
        String[] deadlineSplit = input.split("/by", 2);
        String deadlineBy = deadlineSplit[1];
        String[] deadlineAndName = deadlineSplit[0].split(" ", 2);
        String deadlineName = deadlineAndName[1];
        Deadline deadlineBeingAdded = new Deadline(deadlineName, deadlineBy);
        taskList.addTask(deadlineBeingAdded);
    }
}