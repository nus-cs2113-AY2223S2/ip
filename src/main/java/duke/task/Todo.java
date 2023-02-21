package duke.task;

public class Todo extends Task{
    public Todo(String command) {
        super();
        setInitCommand(command);
        String description = command.substring(command.indexOf(" ")).trim();
        setDescription(description);
    }
    @Override
    public String toString(){
        return "[T][" + (isDone() ? 'X' : ' ') + "]//////" + super.toString();
    }
}
