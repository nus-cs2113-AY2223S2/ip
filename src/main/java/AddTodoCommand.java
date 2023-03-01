public class AddTodoCommand extends Command{
    public AddTodoCommand(String userInput){
        super("add todo");
        content = userInput.replace("todo", " ").strip();
    }
}