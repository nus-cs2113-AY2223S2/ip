import duke.task.ToDo;

import javax.lang.model.element.UnknownElementException;

public class ToDoCommand extends Command{
    private ToDo todo;
    public void setToDo(String input) {
            String[] temp = input.split(" ", 2);
            String description = temp[1];
            this.todo = new ToDo(description);
    }
    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        try {
            setToDo(input);
            tasks.addTask(todo);
            ui.printTaskAddedStatement(tasks, todo);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyTodoMessage();
        }
    }
}
