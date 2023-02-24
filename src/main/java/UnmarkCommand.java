import duke.task.Task;

public class UnmarkCommand extends Command{
    private int index;
    public void setIndex(String input) {
        try {
            String[] temp = input.split(" ", 2);
            int index = Integer.parseInt(temp[1]);
            this.index = index;
        } catch (NumberFormatException exception){
            Messages.invalidTaskMessage();
        }
    }

    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        try{
            setIndex(input);
            Task task = tasks.getTask(index - 1);
            ui.printTaskStatusStatement(task, "unmark");
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidTaskMessage();
        }
    }
}