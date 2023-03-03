/**
 * Represents a command to add todo.
 * A <code>AddTodoCommand</code> object corresponds to a command with "add todo" type and content.
 */

public class AddTodoCommand extends Command{
    public AddTodoCommand(String userInput){
        super("add todo");
        String tempContent = userInput.replace("todo", "").strip();
        super.content = tempContent;
    }
}