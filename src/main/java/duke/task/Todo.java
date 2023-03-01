package duke.task;

public class Todo extends Task{

    /*
     * Initialises the todo task from user command
     *
     * @param command User command
     */
    public Todo(String command) {
        super();
        setInitCommand(command);
        String description = command.substring(command.indexOf(" ")).trim();
        setDescription(description);
    }
    @Override
    /*
     * Gets a printable formatted string of the todo task
     *
     * @return String of the todo task
     */
    public String toString(){
        return "[T][" + (isDone() ? 'X' : ' ') + "]//////" + super.toString();
    }
}
