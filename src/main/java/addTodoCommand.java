

public class addTodoCommand extends Command{
    @Override
    public void executeCommand(TaskList taskList, String input) {
        String[] todoSplit = input.split(" ", 2);
        Todo todoBeingAdded = new Todo(todoSplit[1]);
        taskList.addTask(todoBeingAdded);
    }
}
