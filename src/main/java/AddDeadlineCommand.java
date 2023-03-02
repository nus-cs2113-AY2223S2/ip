

public class AddDeadlineCommand extends Command{
    @Override
    public void executeCommand(TaskList taskList, String input) {
        String[] deadlineSplit = input.split("/by", 2);
        String deadlineBy = deadlineSplit[1].trim();
        String[] deadlineAndName = deadlineSplit[0].split(" ", 2);
        String deadlineName = deadlineAndName[1].trim();
        Deadline deadlineBeingAdded = new Deadline(deadlineName, deadlineBy);
        taskList.addTask(deadlineBeingAdded);
    }
}