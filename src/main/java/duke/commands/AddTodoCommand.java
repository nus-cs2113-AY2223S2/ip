package duke.commands;

import duke.data.Todo;

/**
 * Adds a todo task to the TaskList.
 */
public class AddTodoCommand extends Command{
    public static final String COMMAND_WORD = "todo";
    private final Todo toAdd;

    /**
     *  Convenience constructor using raw values.
     * @param content the description of the todo task
     */
    public AddTodoCommand(String content){
        this.toAdd = new Todo(content);
    }

    public void execute() {
        taskList.addTask(toAdd);
        System.out.println("Got it. I've added this task: ");
        System.out.println(toAdd);
        taskList.printSize();
    }


}
