package duke.commands;

import duke.data.Todo;

public class AddTodoCommand extends Command{
    public static final String COMMAND_WORD = "todo";
    private final Todo toAdd;

    public AddTodoCommand(String content){
        this.toAdd = new Todo(content);
    }

    public Todo getTodo() {
        return toAdd;
    }

    public void execute() {
        taskList.addTask(toAdd);
        System.out.println("Got it. I've added this task: ");
        System.out.println(toAdd);
        taskList.printSize();
    }


}
