public class MarkTaskCommand extends Command {
    @Override
    public void executeCommand(TaskList taskList, String input) {
        try{
            String[] markSplit = input.split(" ", 2);
            int taskNumberToBeMarked = Integer.parseInt(markSplit[1]);
            int indexOfTaskToBeMarked = taskNumberToBeMarked - 1;
            Task taskToBeMarked = taskList.get(indexOfTaskToBeMarked);
            taskToBeMarked.markAsDone();

        }catch(IndexOutOfBoundsException e){
            System.out.println("That is not a valid task to mark! Please check your list again and input a valid task");
        }

    }
}
