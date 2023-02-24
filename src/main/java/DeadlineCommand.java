import duke.task.Deadline;

public class DeadlineCommand extends Command {
    private Deadline deadline;

    public void setDeadline(String input) {
        try {
            String[] temp = input.split("deadline | /by "); //separates deadline description and time
            String description = temp[1];
            String by = temp[2];
            this.deadline = new Deadline(description, by);
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidTaskMessage();
        }
    }

    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        setDeadline(input);
        tasks.addTask(deadline);
        ui.printTaskAddedStatement(tasks, deadline);
    }
}
