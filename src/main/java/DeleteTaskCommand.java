public class DeleteTaskCommand extends Command{

    @Override
    public void executeCommand(TaskList taskList, String input) {
        String[] deleteSplit = input.split(" ", 2);
        int taskNumberToBeDeleted = Integer.parseInt(deleteSplit[1]);
        int indexOfTaskInTaskList = taskNumberToBeDeleted - 1;
        taskList.deleteTask(indexOfTaskInTaskList);


    }
}
