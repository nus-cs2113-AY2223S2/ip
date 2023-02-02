public class Todo extends Task {

    public static Todo toTodo(String instruction){
        return new Todo(instruction);
    }

    public Todo(String description){
        super(description, 'T');
    }
}
