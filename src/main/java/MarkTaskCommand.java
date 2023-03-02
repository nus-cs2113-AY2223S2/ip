public class MarkTaskCommand extends Command {
    @Override
    public void executeCommand(TaskList taskList, String input) {
        String[] markSplit = input.split(" ", 2);
        int taskNumberToBeMarked = Integer.parseInt(markSplit[1]);
        int indexOfTaskToBeMarked = taskNumberToBeMarked - 1;
        Task taskToBeMarked = taskList.get(indexOfTaskToBeMarked);
        taskToBeMarked.markAsDone();
    }
}
