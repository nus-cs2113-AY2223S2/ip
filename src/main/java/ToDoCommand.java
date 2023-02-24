import duke.task.ToDo;

public class ToDoCommand extends Command{
    private ToDo todo;
    public void setToDo(String input) {
        try {
            String[] temp = input.split(" ", 2);
            String description = temp[2];
            this.todo = new ToDo(description);
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidTaskMessage();
        }
    }
    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        setToDo(input);
        tasks.addTask(todo);
        ui.printTaskAddedStatement(tasks, todo);
    }
}
