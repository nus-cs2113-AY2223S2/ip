public class Todo extends Task{
    public Todo(String command) {
        super();
        String description = command.substring(command.indexOf(" ")).trim();
        setDescription(description);
    }
    @Override
    public String toString(){
        return "[T]["+(isDone()?'X':' ')+"]//////"+super.toString();
    }
}
