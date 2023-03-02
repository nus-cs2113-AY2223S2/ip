public class UnmarkTaskCommand extends Command {
    @Override
    public void executeCommand(TaskList taskList, String input) {
        String[] unmarkSplit = input.split(" ", 2);
        int taskNumberToBeUnmarked = Integer.parseInt(unmarkSplit[1]);
        int indexOfTaskToBeUnmarked = taskNumberToBeUnmarked - 1;
        Task taskToBeUnmarked = taskList.get(indexOfTaskToBeUnmarked);
        taskToBeUnmarked.markAsUndone();
    }
}