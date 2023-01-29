public class Todo extends Task{
    public Todo(String description){
        super(description);
        String[] splitDescription = description.split(" ", 2);
        this.description = splitDescription[1];
        this.label = "[T]";
    }
}
