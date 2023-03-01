public class AddTodoCommand extends Command{
    public AddTodoCommand(String userInput){
        super("add todo");
        String tempContent = userInput.replace("todo", " ").strip();
        super.content = tempContent;
    }
}